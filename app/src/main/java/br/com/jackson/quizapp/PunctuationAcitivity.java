package br.com.jackson.quizapp;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PunctuationAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punctuation_acitivity);

        Bundle extras = getIntent().getExtras();

        String userName = "";

        if (extras != null) {

            if (extras.getString(MainActivity.EXTRA_USER_NAME) != null) {
                userName = extras.getString(MainActivity.EXTRA_USER_NAME);
            }

            int punctuation = extras.getInt(QuizActivity.EXTRA_PUNCTUATION);
            int totalQuestions = extras.getInt(QuizActivity.EXTRA_TOTAL_QUESTIONS);

            Resources resources = getResources();
            String yourPunctuation = String.format(resources.getString(R.string.punctuation), userName);
            String assertiveness = String.format(resources.getString(R.string.assertiveness), punctuation, totalQuestions);

            TextView yourPunctuationText = (TextView) findViewById(R.id.your_punctuation);
            yourPunctuationText.setText(String.valueOf(yourPunctuation));

            TextView userNameText = (TextView) findViewById(R.id.punctuation);
            userNameText.setText(assertiveness);
        }

        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PunctuationAcitivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}