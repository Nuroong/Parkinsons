package com.bignerdranch.android.parkinsons;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import layout.MainFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, LoadingActivity.class)); //splash

        FragmentManager fm = getFragmentManager();

        MainFragment mainFragment1 = MainFragment.newInstance(0);

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_space1, mainFragment1);
        Log.i(TAG, "Fragment1 replaced");

        MainFragment mainFragment2 = MainFragment.newInstance(1);

        fragmentTransaction.replace(R.id.fragment_space2, mainFragment2);
        Log.i(TAG, "Fragment2 replaced");
        fragmentTransaction.commit();
        Log.i(TAG, "Fragment transaction committed");

        setContentView(R.layout.activity_main);
    }
}
