package br.com.jackson.quizapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PunctuationAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punctuation_acitivity);

        setExtras();

        Button restartButton = (Button) findViewById(R.id.restart);
        restartButton.setOnClickListener(getListenerRestartButton());
    }

    private View.OnClickListener getListenerRestartButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PunctuationAcitivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    private void setExtras() {
        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            String userName = "";

            if (extras.getString(Constants.EXTRA_USER_NAME) != null) {
                userName = extras.getString(Constants.EXTRA_USER_NAME);
            }

            int punctuation = extras.getInt(Constants.EXTRA_PUNCTUATION);
            int totalQuestions = extras.getInt(Constants.EXTRA_TOTAL_QUESTIONS);

            Resources resources = getResources();
            String yourPunctuation = String.format(resources.getString(R.string.punctuation), userName);
            String assertiveness = String.format(resources.getString(R.string.assertiveness), punctuation, totalQuestions);

            TextView yourPunctuationText = (TextView) findViewById(R.id.your_punctuation);
            yourPunctuationText.setText(String.valueOf(yourPunctuation));

            TextView userNameText = (TextView) findViewById(R.id.punctuation);
            userNameText.setText(assertiveness);
        }
    }
}