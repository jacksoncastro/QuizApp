package br.com.jackson.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.dao.ItemDAO;
import br.com.jackson.quizapp.dao.QuizDAO;
import br.com.jackson.quizapp.enums.LevelEnum;
import br.com.jackson.quizapp.model.Item;
import br.com.jackson.quizapp.model.Quiz;

public class QuizActivity extends AppCompatActivity {

    private Quiz currentQuestion;
    private int currentPosition = 0;
    private int punctuation = 0;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ListView listView = (ListView) findViewById(R.id.itens);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setItemChecked(0, true);

        Bundle extras = getIntent().getExtras();

        LevelEnum levelEnum = LevelEnum.EASY;

        if (extras != null) {
            if (extras.get(Constants.EXTRA_USER_NAME) != null) {
                userName = extras.getString(Constants.EXTRA_USER_NAME);
            }
            if (extras.get(Constants.EXTRA_LAST_QUESTION_POSITION) != null) {
                currentPosition = extras.getInt(Constants.EXTRA_LAST_QUESTION_POSITION);
            }
            if (extras.get(Constants.EXTRA_PUNCTUATION) != null) {
                punctuation = extras.getInt(Constants.EXTRA_PUNCTUATION);
            }
        }

        List<Quiz> quizList = getQuiz(levelEnum);

        if (quizList.size() <= currentPosition) {
            Intent intent = new Intent(QuizActivity.this, PunctuationAcitivity.class);
            intent.putExtra(Constants.EXTRA_TOTAL_QUESTIONS, quizList.size());
            intent.putExtra(Constants.EXTRA_PUNCTUATION, punctuation);
            intent.putExtra(Constants.EXTRA_USER_NAME, userName);

            startActivity(intent);
            finish();
        } else {
            currentQuestion = quizList.get(currentPosition);

            ImageView imageView = (ImageView) findViewById(R.id.imageQuiz);

            Uri uri = Uri.parse(currentQuestion.getImageLink());
            Picasso.with(QuizActivity.this).load(uri).error(R.drawable.image_not_found).into(imageView);

            TextView question = (TextView) findViewById(R.id.question);
            question.setText(currentQuestion.getQuestion());

            List<String> items = getItemsToArrayString(currentQuestion.getItems());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, items);

            listView.setAdapter(adapter);

            final Button checkQuestionButton = (Button) findViewById(R.id.checkQuestionButton);
            checkQuestionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = listView.getCheckedItemPosition();

                    if (currentQuestion.getRightAnswer() == position) {
                        Toast.makeText(QuizActivity.this, "Resposta certa", Toast.LENGTH_SHORT).show();
                        punctuation++;
                    } else {
                        Toast.makeText(QuizActivity.this, "Resposta errada.", Toast.LENGTH_SHORT).show();
                    }

                    Intent intent = new Intent(QuizActivity.this, QuizActivity.class);
                    intent.putExtra(Constants.EXTRA_LAST_QUESTION_POSITION, currentPosition + 1);
                    intent.putExtra(Constants.EXTRA_PUNCTUATION, punctuation);
                    intent.putExtra(Constants.EXTRA_USER_NAME, userName);

                    startActivity(intent);
                    finish();
                }
            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView arg0, View view, int position, long itemId) {
                    if (checkQuestionButton != null && !checkQuestionButton.isEnabled()) {
                        checkQuestionButton.setEnabled(true);
                    }
                }
            });
        }
    }

    private List<String> getItemsToArrayString(List<Item> items) {
        List<String> list = new ArrayList<>();
        for (Item item : items) {
            list.add(item.getAnswer());
        }
        return list;
    }

    public List<Quiz> getQuiz(LevelEnum levelEnum) {
        QuizDAO quizDAO = new QuizDAO(QuizActivity.this);

        try {
            List<Quiz> quizList = quizDAO.findAllRandomWithLimit(levelEnum.getAmount());

            for (Quiz quiz : quizList) {
                List<Item> items = findItemsByQuizId(quiz.getId());

                quiz.setItems(items);
            }
            return  quizList;
        } finally {
            if (quizDAO != null) {
                quizDAO.close();
            }
        }
    }

    private List<Item> findItemsByQuizId(Integer quizId) {
        ItemDAO itemDAO = new ItemDAO(QuizActivity.this);

        try {
            return itemDAO.findItemsByQuiz(quizId);
        } finally {

            if (itemDAO != null) {
                itemDAO.close();
            }
        }
    }
}