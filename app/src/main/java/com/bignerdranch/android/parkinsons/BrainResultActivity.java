package com.bignerdranch.android.parkinsons;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class BrainResultActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "com.bignerdranch.android.parkinsons.extra_score";
    private String TAG = "BrainResult";

    private DialChartView mDialChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_result);

        setTitle(R.string.title_activity_brain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        int score = 0;
        score = getIntent().getIntExtra(EXTRA_SCORE, score);

        TextView res_view = (TextView) findViewById(R.id.res_brain);
        res_view.setText("나의 검사 결과는 : " + score * 5);

        FrameLayout dynamicLayout = (FrameLayout) findViewById(R.id.brain_res_layout);
        FrameLayout.LayoutParams layoutParam = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        layoutParam.gravity = Gravity.CENTER;

        //차트를 출력하는 뷰객체(ChartView) 생성
        mDialChartView = new DialChartView(this);
        dynamicLayout.addView(mDialChartView, layoutParam);
        mDialChartView.makeBrainDialChart();            //차트 그리기

        ImageView mNeedle = (ImageView) findViewById(R.id.chart_needle);
        dynamicLayout.removeView(mNeedle);
        mNeedle.setImageBitmap(makeNeedle(score));
        dynamicLayout.addView(mNeedle, layoutParam);
    }
    public Bitmap makeNeedle(int angle) {
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.needle_black);

        Matrix mMatrix = new Matrix();
        mMatrix.postRotate((float) angle * 10);

        Log.i(TAG, "angle : " + angle * 10);

        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), mMatrix, true);
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
