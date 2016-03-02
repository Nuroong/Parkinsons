package com.bignerdranch.android.parkinsons;


import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private static final String DIALOG_AGE = "age";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, LoadingActivity.class));

        setContentView(R.layout.activity_main);

        ImageButton startBtn = (ImageButton) findViewById(R.id.BtntestBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(0);
                mAgeDialog.show(fm, DIALOG_AGE);
            }
        });

        ImageButton shakeBtn = (ImageButton) findViewById(R.id.ShaketestBtn);
        shakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(1);
                mAgeDialog.show(fm, DIALOG_AGE);
            }
        });

        ImageButton brainBtn = (ImageButton) findViewById(R.id.BrainBtn);
        brainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                AgeDialogFragment mAgeDialog = AgeDialogFragment.newInstance(2);
                mAgeDialog.show(fm, DIALOG_AGE);
            }
        });

        ImageButton infoBtn = (ImageButton) findViewById(R.id.HowtoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), HowtoActivity.class));
            }
        });
    }
}
