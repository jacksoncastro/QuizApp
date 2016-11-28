package br.com.jackson.quizapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.jackson.quizapp.adapters.MyCustomAdapter;

public class FormQuizActivity extends AppCompatActivity {

    private List<String> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_quiz);

        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(answers, this);

        //handle listview and assign adapter
        final ListView listView = (ListView)findViewById(R.id.answers);
        listView.setAdapter(adapter);

        Button addAnswerButton = (Button) findViewById(R.id.add_answer);
        addAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editText = (EditText) findViewById(R.id.new_answer);

                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(FormQuizActivity.this, R.string.field_empty, Toast.LENGTH_SHORT).show();
                } else {
                    addNewAnswer(editText, listView);
                }
            }
        });
    }


    private void addNewAnswer(EditText editText, ListView listView) {
        answers.add(editText.getText().toString());

        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(answers, FormQuizActivity.this);

        listView.setAdapter(adapter);

        editText.setText(null);

        closeKeyboard(listView);
    }

    private void closeKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok :
                Toast.makeText(FormQuizActivity.this, "Chora", Toast.LENGTH_SHORT).show();
//                salvarFormulario();
//                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}