package br.com.jackson.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jackson.quizapp.adapters.MyItemInfoAdapter;
import br.com.jackson.quizapp.dao.ItemDAO;
import br.com.jackson.quizapp.dao.RightAnswerDAO;
import br.com.jackson.quizapp.model.Item;
import br.com.jackson.quizapp.model.Quiz;

public class QuestionInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_info);

        // set data for list info
        setDataInfo();
    }

    private void setDataInfo() {
        Quiz quiz = getIntent().getParcelableExtra(Constants.EXTRA_QUIZ);

        List<Item> items = getItemsQuiz(quiz);

        quiz.setItems(items);

        TextView questionInfoTitle = (TextView) findViewById(R.id.question_info_title);
        questionInfoTitle.setText(quiz.getQuestion());

        // define image of item
        ImageView questionInfoImage = (ImageView) findViewById(R.id.question_info_image);
        Picasso.with(QuestionInfoActivity.this).load(quiz.getImageLink()).error(R.drawable.image_not_found).resize(100, 100).into(questionInfoImage);

        int rightItemIdQuiz = getRightItemIdQuiz(quiz);

        createRecyclerView(items, rightItemIdQuiz);
    }

    private void createRecyclerView(List<Item> items, int rightItemIdQuiz) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_question_info);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MyItemInfoAdapter myRecyclerViewAdapter = new MyItemInfoAdapter(items, rightItemIdQuiz);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }


    private int getRightItemIdQuiz(Quiz quiz) {
        RightAnswerDAO rightAnswerDAO = new RightAnswerDAO(QuestionInfoActivity.this);

        try {
            return rightAnswerDAO.getRightItemIdQuiz(quiz.getId());
        } finally {
            if (rightAnswerDAO != null) {
                rightAnswerDAO.close();
            }
        }
    }

    private List<Item> getItemsQuiz(Quiz quiz) {

        ItemDAO itemDAO = new ItemDAO(QuestionInfoActivity.this);

        try {
            return itemDAO.findItemsByQuiz(quiz.getId());
        } finally {
            if (itemDAO != null) {
                itemDAO.close();
            }
        }
    }
}