package com.bignerdranch.android.parkinsons;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;

import layout.HowtoFragment;


public class HowtoActivity extends AppCompatActivity {
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<HowtoContents> mHowtoContents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);

        setTitle(R.string.title_activity_howto);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mPager = (ViewPager) findViewById(R.id.main_viewpager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mHowtoContents.add(new HowtoContents(R.drawable.diag_info, "진단법 안내", "파킨슨의 주요 증상인 경직, 떨림 등을 검사할 수 있습니다."));
        mHowtoContents.add(new HowtoContents(R.drawable.screenshot_btntest, "누르기 검사", "빨간 버튼을 순서대로 끝까지 누르시면 됩니다."));
        mHowtoContents.add(new HowtoContents(R.drawable.screenshot_relex, "손떨림 검사", "그림과 같이 편히 앉아서 버튼을 누른 후 진동이 울릴때까지 장치를 손에 쥐고 계시면 됩니다."));
        mHowtoContents.add(new HowtoContents(R.drawable.screenshot_stretch, "손떨림 검사", "그림과 같이 팔을 뻗고 버튼을 누른 후 진동이 울릴때까지 장치를 손에 쥐고 계시면 됩니다."));
        mHowtoContents.add(new HowtoContents(R.drawable.screenshot_braintest, "인지력 검사", "제시된 질문 중 해당되는 것에 체크해주시면 됩니다."));
        mHowtoContents.add(new HowtoContents(R.drawable.screenshot_shakeresult, "검사 결과 안내", "당신의 검사 결과를 일반인과 환자의 표준과 함께 분석하여 알려드립니다."));
    }

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Fragment getItem(int position) {
            // 해당하는 page의 Fragment를 생성합니다.
            HowtoFragment fragment = new HowtoFragment();

            Bundle args = new Bundle();
            args.putInt(HowtoFragment.ARG_PARAM1, mHowtoContents.get(position).getmHowtoImage());
            args.putString(HowtoFragment.ARG_PARAM2, mHowtoContents.get(position).getmHowtoTitle());
            args.putString(HowtoFragment.ARG_PARAM3, mHowtoContents.get(position).getmHowtoText());
            fragment.setArguments(args);
            return fragment;
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
