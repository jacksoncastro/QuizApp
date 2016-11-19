package br.com.jackson.quizapp;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.model.Quiz;

public class QuizActivity extends AppCompatActivity {

    public static final String EXTRA_LAST_QUESTION_POSITION = "LAST_QUESTION_POSITION";
    public static final String EXTRA_PUNCTUATION = "PUNCTUATION";
    public static final String EXTRA_TOTAL_QUESTIONS = "TOTAL_QUESTIONS";

    private Quiz currentQuestion;
    private int currentPosition = 0;
    private int punctuation = 0;
    private String userName;

    private static List<Quiz> quizList = new ArrayList<Quiz>();

    static {
        quizList.add(new Quiz(R.drawable.yugioh, "Em YuGiOh! qual o verdadeiro nome de Yami Yugi, faraó que habita o corpo de Yugi Muto?", new String[] {"Naomy", "Atem", "Rá", "Kora"}, 1));
        quizList.add(new Quiz(R.drawable.xadrez, "O que significa a expressão do jogo de Xadrez \"Xeque mate\"?", new String[] {"Fim de jogo", "Eu venci", "Rei morto", "Adversário eliminado"}, 2));
        quizList.add(new Quiz(R.drawable.tartaruga, "Que animal, em média, vive mais?", new String[] {"O chimpanzé", "O coelho", "O porco", "O rinoceronte"}, 3));
        quizList.add(new Quiz(R.drawable.temperatura, "Qual a temperatura necessária para a gasolina congelar?", new String[] {"-117,5 graus centigrados", "-325 graus centigrados", "-100 graus centigrados", "Nunca congela"}, 3));
        quizList.add(new Quiz(R.drawable.corpo, "Qual a única parte do corpo humano não recebe sangue? Sem considerar, unhas, cabelos, pelos e dentes.", new String[] {"A parte posterior do cérebro", "A Córnea", "A Lingua", "O Apêndice"}, 1));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        final ListView listView = (ListView) findViewById(R.id.itens);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setItemChecked(0, true);

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            if (extras.get(MainActivity.EXTRA_USER_NAME) != null) {
                userName = extras.getString(MainActivity.EXTRA_USER_NAME);
            }
            if (extras.get(EXTRA_LAST_QUESTION_POSITION) != null) {
                currentPosition = extras.getInt(EXTRA_LAST_QUESTION_POSITION);
            }
            if (extras.get(EXTRA_PUNCTUATION) != null) {
                punctuation = extras.getInt(EXTRA_PUNCTUATION);
            }
        }

        if (quizList.size() <= currentPosition) {
            Intent intent = new Intent(QuizActivity.this, PunctuationAcitivity.class);
            intent.putExtra(EXTRA_TOTAL_QUESTIONS, quizList.size());
            intent.putExtra(EXTRA_PUNCTUATION, punctuation);
            intent.putExtra(MainActivity.EXTRA_USER_NAME, userName);

            startActivity(intent);
            finish();
        } else {
            currentQuestion = quizList.get(currentPosition);

            ImageView imageView = (ImageView) findViewById(R.id.imageQuiz);
            imageView.setImageResource(currentQuestion.getImageId());

            TextView question = (TextView) findViewById(R.id.question);
            question.setText(currentQuestion.getQuestion());

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, currentQuestion.getItens());

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
                    intent.putExtra(EXTRA_LAST_QUESTION_POSITION, currentPosition + 1);
                    intent.putExtra(EXTRA_PUNCTUATION, punctuation);
                    intent.putExtra(MainActivity.EXTRA_USER_NAME, userName);

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
}