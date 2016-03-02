package com.bignerdranch.android.parkinsons;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TouchTestActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG = "ShakeTest";

    private int target_n = -1;
    private double curtime = 0;
    private double pasttime = 0;
    private double start_time;
    private double sum = 0;
    private double sqrsum = 0;
    private double dev = 0;
    private double[] gap_data = new double[11];
    private Button[] Btns = new Button[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);

        setTitle(R.string.title_activity_touch);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Btns[0] = (Button) findViewById(R.id.button1);
        Btns[1] = (Button) findViewById(R.id.button2);
        Btns[2] = (Button) findViewById(R.id.button3);
        Btns[3] = (Button) findViewById(R.id.button4);
        Btns[4] = (Button) findViewById(R.id.button5);
        Btns[5] = (Button) findViewById(R.id.button6);
        Btns[6] = (Button) findViewById(R.id.button7);
        Btns[7] = (Button) findViewById(R.id.button8);
        Btns[8] = (Button) findViewById(R.id.button9);
        Btns[9] = (Button) findViewById(R.id.button10);
        Btns[10] = (Button) findViewById(R.id.button11);
        Btns[11] = (Button) findViewById(R.id.button12);

        targetSet();
    }
    public void targetSet() {
        Button next_target = Btns[++target_n];
        next_target.setTextColor(Color.BLACK);
        next_target.setBackgroundResource(R.drawable.target_shape);
        next_target.setOnClickListener(this);

        Log.i(TAG, "Set next target button : " + target_n);
    }

    public void onClick(View v) {
        int x = Integer.parseInt(((Button) v).getText().toString()) - 1;    //버튼의 번호 가져옴

        if(x == target_n) {    //버튼 번호가 현재의 타겟인 경우
            Button target = Btns[target_n];
            target.setVisibility(View.INVISIBLE);

            if(x == 0) {    //1번일 땐 start time 지정
                start_time = (double) System.nanoTime();
                Log.i(TAG, "Set start time");
                targetSet();    //다음 타겟 버튼 셋팅
            }
            else {
                curtime = (double) System.nanoTime() - start_time;
                gap_data[target_n-1] = (curtime - pasttime)/1000000000;   //버튼 눌린 시간 간의 gap 저장
                Log.i(TAG, "Get gap: " + gap_data[target_n-1]);

                sum += gap_data[target_n-1];
                sqrsum += (gap_data[target_n-1] * gap_data[target_n-1]);
                pasttime = curtime;

                if (target_n == 11) {   //마지막 버튼이 눌렸을 경우 결과 액티비티로
                    dev = Math.round(Math.sqrt((sqrsum/11) - (sum/11) * (sum/11)) * 10000d) / 100d;//버튼 눌린 시간 간의 편차 계산
                    startActivity(new Intent(getApplication(), TouchResultActivity.class)
                            .putExtra(TouchResultActivity.EXTRA_GAP, gap_data)
                            .putExtra(TouchResultActivity.EXTRA_RESULT, dev));
                    finish();
                }
                else {
                    targetSet();    //다음 타겟 버튼 셋팅
                }
            }
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
