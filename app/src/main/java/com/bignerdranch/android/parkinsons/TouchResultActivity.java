package com.bignerdranch.android.parkinsons;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import layout.TouchResultPagerFragment;

public class TouchResultActivity extends AppCompatActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public static final String EXTRA_GAP = "com.bignerdranch.android.parkinsons.extra_gap";
    public static final String EXTRA_RESULT = "com.bignerdranch.android.parkinsons.extra_result";
    private double[] gap_data = new double[11];
    private double touch_result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle(R.string.title_activity_touch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        gap_data = getIntent().getDoubleArrayExtra(EXTRA_GAP);
        touch_result = getIntent().getDoubleExtra(EXTRA_RESULT, touch_result);

        TextView dev_view = (TextView) findViewById(R.id.res_text);
        dev_view.setText("나의 검사 결과는 : " + touch_result);

        mPager = (ViewPager) findViewById(R.id.res_viewpager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.

            Bundle args = new Bundle();
            args.putInt(TouchResultPagerFragment.ARG_PARAM1, position);
            args.putDouble(TouchResultPagerFragment.ARG_PARAM2, touch_result);
            args.putDoubleArray(TouchResultPagerFragment.ARG_PARAM3, gap_data);

            TouchResultPagerFragment TRPF = new TouchResultPagerFragment();
            TRPF.setArguments(args);

            return TRPF;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}