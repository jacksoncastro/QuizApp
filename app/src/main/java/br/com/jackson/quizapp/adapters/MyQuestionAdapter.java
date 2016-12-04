package br.com.jackson.quizapp.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.jackson.quizapp.Constants;
import br.com.jackson.quizapp.QuestionInfoActivity;
import br.com.jackson.quizapp.R;
import br.com.jackson.quizapp.model.Quiz;

public class MyQuestionAdapter extends RecyclerView.Adapter<MyQuestionAdapter.ViewHolder> {

    private List<Quiz> quizList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public View getView() {
            return view;
        }

        public void setView(View view) {
            this.view = view;
        }
    }

    public MyQuestionAdapter(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public MyQuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return quizList.get(position).getId();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Quiz quiz = quizList.get(position);

        final TextView infoText = (TextView) holder.view.findViewById(R.id.info_text);

        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_item_card_view);

        Uri uri = Uri.parse(quiz.getImageLink());

        Picasso.with(holder.view.getContext()).load(uri).error(R.drawable.image_not_found).into(imageView);

        infoText.setText(quiz.getQuestion());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.view.getContext(), QuestionInfoActivity.class);
                intent.putExtra(Constants.EXTRA_QUIZ, quiz);
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return quizList.size();
    }
}