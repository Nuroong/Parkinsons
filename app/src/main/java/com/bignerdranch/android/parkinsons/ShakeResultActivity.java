package com.bignerdranch.android.parkinsons;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import layout.ShakeResultPagerFragment;

public class ShakeResultActivity extends AppCompatActivity {
    private double shake_result;
    private double[] test_data = new double[10];
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    public static final String SHAKE_RESULT = "com.bignerdranch.android.parkinsons.stretch_result";
    public static final String TEST_DATA = "com.bignerdranch.android.parkinsons.test_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        setTitle(R.string.title_activity_shake);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent intent = getIntent();
        shake_result = intent.getDoubleExtra(SHAKE_RESULT, shake_result);
        test_data = intent.getDoubleArrayExtra(TEST_DATA);

        TextView res_view = (TextView) findViewById(R.id.res_text);

        res_view.setText("나의 검사 결과는 : " + shake_result);

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
            args.putInt(ShakeResultPagerFragment.ARG_PARAM1, position);
            args.putDouble(ShakeResultPagerFragment.ARG_PARAM2, shake_result);
            args.putDoubleArray(ShakeResultPagerFragment.ARG_PARAM3, test_data);

            ShakeResultPagerFragment SRPF = new ShakeResultPagerFragment();
            SRPF.setArguments(args);

            return SRPF;
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
