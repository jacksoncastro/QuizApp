package br.com.jackson.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.com.jackson.quizapp.enums.LevelEnum;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.level_game_spinner);
        setItemsSpinner(spinner);

        Button startButton = (Button) findViewById(R.id.start);
        startButton.setOnClickListener(getListenerStartButton(spinner));

        Button addQuestionsButton = (Button) findViewById(R.id.add_questions);
        addQuestionsButton.setOnClickListener(getListenerQuestionButton());

        Button questionsButton = (Button) findViewById(R.id.questions);
        questionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, QuestionsActivity.class);
                startActivity(intent);
            }
        });
    }

    private View.OnClickListener getListenerQuestionButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormQuizActivity.class);
                startActivity(intent);
            }
        };
    }

    private View.OnClickListener getListenerStartButton(final Spinner spinner) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameEdit = (EditText) findViewById(R.id.user_name);
                String userName = userNameEdit.getText().toString();

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                int selectedItemPosition = spinner.getSelectedItemPosition();

                LevelEnum level = LevelEnum.getLevelByPosition(selectedItemPosition);

                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.EXTRA_LEVEL, level);
                bundle.putString(Constants.EXTRA_USER_NAME, userName);

                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        };
    }

    private void setItemsSpinner(Spinner spinner) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.level_game, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}