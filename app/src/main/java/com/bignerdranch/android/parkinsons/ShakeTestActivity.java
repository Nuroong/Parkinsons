package com.bignerdranch.android.parkinsons;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ShakeTestActivity extends AppCompatActivity implements SensorEventListener {
    private String TAG = "ShakeTest";

    private long lastTime;
    private double speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;
    private int count;
    private int rest_count;
    private int stretch_count;
    private double[] shake_data = new double[30];
    private boolean check_on = false;
    public int time = 0;

    private static final int SWING_MOVE = 500;      //팔 움직임 배제
    private static final int SHAKE_THRESHOLD = 150; //떨림 측정 기준

    private SensorManager sensorManager;
    private Sensor accelerormeterSensor;
    private Handler test_handler;
    private Handler check_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_test);

        setTitle(R.string.title_activity_shake);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // 시스템 서비스에서 센서메니져 획득
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        // TYPE_ACCELEROMETER의 기본 센서객체를 획득
        accelerormeterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        final Button startBtn = (Button) findViewById(R.id.shake_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                startBtn.setVisibility(View.INVISIBLE);

                check_handler = new Handler();
                check_handler.post(check_run);  //가속도 센서 check_on
                Log.i(TAG, "Start rest check");

                test_handler = new Handler();
                test_handler.postDelayed(rest_Run, 10 * 1000);
                Log.i(TAG, "Start stretch check");
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        if (accelerormeterSensor != null)
            sensorManager.registerListener(this, accelerormeterSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);
            if (gabOfTime > 100) {
                lastTime = currentTime;
                x = event.values[0];
                y = event.values[1];
                z = event.values[2];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                if (speed < SWING_MOVE){
                    if(speed > SHAKE_THRESHOLD) {
                        count++;        //이건 계속 세고
                        Log.i(TAG, "Shaking counted " + count + " times");
                    }
                    if(check_on) {
                        shake_data[time++] = speed;
                        check_on = false;   //0.3초마다 체크 후 check_off
                        Log.i(TAG, "Shaking checked");
                    }
                }

                lastX = event.values[0];
                lastY = event.values[1];
                lastZ = event.values[2];
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Runnable rest_Run = new Runnable() {
        @Override
        public void run() {
            check_on = false;//가속도 센서 check_off

            rest_count = count; //안정 떨림 횟수 저장
            Log.i(TAG, "Rest shaking : " + rest_count);

            Vibrator mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            mVibe.vibrate(500);     // 1초 = 1000

            TextView help_text = (TextView) findViewById(R.id.shake_help);
            help_text.setText("그림과 같이 장치를 손에 쥐고 팔을 뻗어 주세요.");
            ImageView help_img = (ImageView) findViewById(R.id.shake_image);
            help_img.setImageResource(R.drawable.stretch_hand);

            final Button startBtn = (Button) findViewById(R.id.shake_button);
            startBtn.setVisibility(View.VISIBLE);

            startBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count = 1;

                    startBtn.setVisibility(View.INVISIBLE);
                    test_handler.postDelayed(stretch_Run, 10 * 1000);
                    Log.i(TAG, "Start stretch check");
                }
            });
        }
    };

    private Runnable stretch_Run = new Runnable() {
        @Override
        public void run() {
            stretch_count = count;  //운동 떨림 저장
            Log.i(TAG, "Stretch shaking : " + stretch_count);

            Vibrator mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            mVibe.vibrate(500);

            check_handler.removeMessages(0);
            test_handler.removeMessages(0);
            Log.i(TAG, "Shaking test result is : " + ((rest_count + (rest_count / stretch_count)*337)) / 100d);
            Intent intent = new Intent(getApplication(), ShakeResultActivity.class);
            intent.putExtra(ShakeResultActivity.SHAKE_RESULT, ((rest_count + (rest_count / stretch_count)*337)) / 100d);
            intent.putExtra(ShakeResultActivity.TEST_DATA, shake_data);
            startActivity(intent);
            finish();   //go to result activity
        }
    };

    private Runnable check_run = new Runnable() {
        @Override
        public void run() {
            check_on = true;//가속도 센서 check_on
            check_handler.postDelayed(check_stop, 300);
            Log.i(TAG, "Check run");
        }
    };

    private Runnable check_stop = new Runnable() {
        @Override
        public void run() {
            check_on = false;//가속도 센서 check_off
            if(time < 29) check_handler.post(check_run);
            Log.i(TAG, "Check stop");
        }
    };

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
