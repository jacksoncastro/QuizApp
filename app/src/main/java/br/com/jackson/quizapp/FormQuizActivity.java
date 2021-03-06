package br.com.jackson.quizapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import java.util.List;

import br.com.jackson.quizapp.adapters.MyCustomAdapter;
import br.com.jackson.quizapp.dao.ItemDAO;
import br.com.jackson.quizapp.dao.QuizDAO;
import br.com.jackson.quizapp.dao.RightAnswerDAO;
import br.com.jackson.quizapp.exceptions.FormValidationException;
import br.com.jackson.quizapp.model.Item;
import br.com.jackson.quizapp.model.Quiz;
import br.com.jackson.quizapp.model.RightAnswer;

public class FormQuizActivity extends AppCompatActivity {

    private List<Item> answers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_quiz);

        //instantiate custom adapter
        MyCustomAdapter adapter = new MyCustomAdapter(answers, this);

        //handle listview and assign adapter
        final ListView listView = (ListView)findViewById(R.id.answers);
        listView.setAdapter(adapter);

        final Button addAnswerButton = (Button) findViewById(R.id.add_answer);
        addAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // valid form before add a new answer
                    validFieldAnswer();
                    // add new answer into listview
                    addNewAnswer(listView);
                } catch (FormValidationException e) {
                    Toast.makeText(FormQuizActivity.this, e.getMessageId(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void validFieldAnswer() throws FormValidationException {
        EditText newAnswer = (EditText) findViewById(R.id.new_answer);
        if (newAnswer != null && newAnswer.getText().toString().isEmpty()) {
            throw new FormValidationException(R.string.field_answer_empty);
        }
    }


    private void addNewAnswer(ListView listView) {
        EditText newAnswer = (EditText) findViewById(R.id.new_answer);
        answers.add(new Item(newAnswer.getText().toString()));

        listView.deferNotifyDataSetChanged();

        newAnswer.setText(null);

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
                try {
                    validForm();
                    saveForm();
                    finish();
                } catch (FormValidationException e) {
                    Toast.makeText(FormQuizActivity.this, e.getMessageId(), Toast.LENGTH_SHORT).show();
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void validForm() throws FormValidationException {
        EditText question = (EditText) findViewById(R.id.question);
        if (question != null && question.getText().toString().isEmpty()) {
            throw new FormValidationException(R.string.field_question_empty);
        }

        EditText linkImage = (EditText) findViewById(R.id.link_image);
        if (linkImage != null && linkImage.getText().toString().isEmpty()) {
            throw new FormValidationException(R.string.field_link_image_empty);
        }

        ListView listView = (ListView) findViewById(R.id.answers);
        MyCustomAdapter myCustomAdapter = (MyCustomAdapter) listView.getAdapter();

        int countQuestions = myCustomAdapter.getCount();

        if (countQuestions == 0) {
            throw new FormValidationException(R.string.no_questions_were_added);
        }

        int correctItem = myCustomAdapter.getSelectedPositionRadioButton();

        if (correctItem == -1) {
            throw new FormValidationException(R.string.radio_button_correct_item_empty);
        }
    }

    private void saveForm() {
        ListView listView = (ListView) findViewById(R.id.answers);
        MyCustomAdapter myCustomAdapter = (MyCustomAdapter) listView.getAdapter();

        EditText question = (EditText) findViewById(R.id.question);

        EditText linkImage = (EditText) findViewById(R.id.link_image);

        List<Item> answerList = myCustomAdapter.getAnswerList();

        int correctItem = myCustomAdapter.getSelectedPositionRadioButton();

        Quiz quiz = new Quiz(question.getText().toString(), linkImage.getText().toString(), answerList);

        saveData(quiz, correctItem);

        Toast.makeText(FormQuizActivity.this, R.string.form_quiz_save_success, Toast.LENGTH_SHORT).show();
    }

    private void saveData(Quiz quiz, int correctItemPosition) {
        // save quiz in database
        int quizId = saveQuiz(quiz);

        // save items in database
        int rightItemId = saveItems(quiz.getItems(), quizId, correctItemPosition);

        RightAnswer rightAnswer = new RightAnswer(quizId, rightItemId);

        // save right answer in database
        saveRightAnswer(rightAnswer);
    }

    private int saveQuiz(Quiz quiz) {
        QuizDAO quizDAO = new QuizDAO(FormQuizActivity.this);
        try {
            return quizDAO.insert(quiz);
        } finally {
            if (quizDAO != null) {
                quizDAO.close();
            }
        }
    }

    private int saveItems(List<Item> items, int quizId, int correctItem) {
        int rightItemId = 0;
        ItemDAO itemDAO = new ItemDAO(FormQuizActivity.this);
        try {
            for (int i=0; i < items.size(); i++) {
                items.get(i).setQuizId(quizId);
                int id = itemDAO.insert(items.get(i));
                if (i == correctItem) {
                    rightItemId = id;
                }
            }
        } finally {
            if (itemDAO != null) {
                itemDAO.close();
            }
        }
        return rightItemId;
    }

    private void saveRightAnswer(RightAnswer rightAnswer) {
        RightAnswerDAO rightAnswerDAO = new RightAnswerDAO(FormQuizActivity.this);
        try {
            rightAnswerDAO.insert(rightAnswer);
        } finally {
            if (rightAnswerDAO != null) {
                rightAnswerDAO.close();
            }
        }
    }
}