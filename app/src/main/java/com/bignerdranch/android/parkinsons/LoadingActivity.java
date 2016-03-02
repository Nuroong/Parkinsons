package com.bignerdranch.android.parkinsons;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();       // Close after 2sec
            }
        }, 2000);
    }
}
