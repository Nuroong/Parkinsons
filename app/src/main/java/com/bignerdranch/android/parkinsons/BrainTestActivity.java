package com.bignerdranch.android.parkinsons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class BrainTestActivity extends AppCompatActivity {
    private static final String TAG = "BrainTestActivity";

    private int score = 0;
    private int page_number = 1;
    private BufferedReader br = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brain_test);

        setTitle(R.string.title_activity_brain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        try {
            br = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.question), "MS949"));
            Log.i(TAG, "Read and Encoding Success");
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Read and Encoding ERROR : ", e);
        }

        setQuestion();
    }
    @Override
    protected void onResume() {
        super.onResume();

        ImageButton nextBtn = (ImageButton) findViewById(R.id.nextButton);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView help_text = (TextView) findViewById(R.id.helpText);
                TextView nextPage = (TextView) findViewById(R.id.nextText);

                score_check(page_number);

                switch (++page_number) {
                    case 8:
                        help_text.setText("다음 단어를 기억해주세요");
                        nextPage.setText("다음 질문(" + page_number + "/10)");
                        break; //tree, car, cap
                    case 10:
                        help_text.setText("기억나는 단어를 선택하세요.");
                        nextPage.setText("결과 보기(" + page_number + "/10)");
                        break;  //tree, car, cap repeat
                    case 11:
                        startActivity(new Intent(getApplication(), BrainResultActivity.class).putExtra(BrainResultActivity.EXTRA_SCORE, score));
                        finish();
                        break;
                    default:
                        help_text.setText("해당되는 질문에 모두 체크하세요.");
                        nextPage.setText("다음 질문(" + page_number + "/10)");
                }
                setQuestion();
            }
        });
    }

    private void score_check(int pagenumber) {
        CheckBox score_box = null;

        if(pagenumber == 8) return;
        else {
            for (int i = 0; i < 4; i++) {
                switch (i) {
                    case 0: score_box = (CheckBox) findViewById(R.id.check_1); break;
                    case 1: score_box = (CheckBox) findViewById(R.id.check_2); break;
                    case 2: score_box = (CheckBox) findViewById(R.id.check_3); break;
                    case 3: score_box = (CheckBox) findViewById(R.id.check_4); break;
                }
                if (pagenumber == 10 && score_box.isChecked() == false) score++;
                else if (pagenumber != 10 && score_box.isChecked()) score++;
            }
        }
    }

    public void setQuestion() {
        CheckBox question_box = null;
        for(int i=0; i<4; i++) {
            try {
                String line = br.readLine();
                if (line == null) break;
                else {
                    switch (i) {
                        case 0: question_box = (CheckBox) findViewById(R.id.check_1); break;
                        case 1: question_box = (CheckBox) findViewById(R.id.check_2); break;
                        case 2: question_box = (CheckBox) findViewById(R.id.check_3); break;
                        case 3: question_box = (CheckBox) findViewById(R.id.check_4); break;
                    }
                    question_box.setChecked(false);
                    question_box.setText(line);
                    Log.i(TAG, "Line Read : " + line);
                }
            } catch (IOException e) {
                Log.e(TAG, "Line Read ERROR : ", e);
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
