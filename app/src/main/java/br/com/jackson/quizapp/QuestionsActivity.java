package br.com.jackson.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.jackson.quizapp.adapters.MyQuestionAdapter;
import br.com.jackson.quizapp.dao.QuizDAO;
import br.com.jackson.quizapp.model.Quiz;

public class QuestionsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_questions);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List<Quiz> quizList = findAllQuiz();

        MyQuestionAdapter myRecyclerViewAdapter = new MyQuestionAdapter(quizList);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public List<Quiz> findAllQuiz() {
        QuizDAO quizDAO = new QuizDAO(QuestionsActivity.this);
        try {
            return quizDAO.findAll();
        } finally {
            quizDAO.close();
        }
    }
}