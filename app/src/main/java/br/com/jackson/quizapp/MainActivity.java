package br.com.jackson.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_USER_NAME = "USER_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameEdit = (EditText) findViewById(R.id.user_name);
                String userName = userNameEdit.getText().toString();

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra(EXTRA_USER_NAME, userName);
                startActivity(intent);
                finish();
            }
        });
    }
}