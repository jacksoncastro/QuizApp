package br.com.jackson.quizapp.adapters;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;

import br.com.jackson.quizapp.R;
import br.com.jackson.quizapp.model.Quiz;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

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

    public MyRecyclerViewAdapter(List<Quiz> quizList) {
        this.quizList = quizList;
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TextView infoText = (TextView) holder.view.findViewById(R.id.info_text);

        ImageView imageView = (ImageView) holder.view.findViewById(R.id.image_item_card_view);

        Uri uri = Uri.parse("http://i.imgur.com/DvpvklR.png");

        Picasso.with(holder.view.getContext()).load(uri).error(R.drawable.corpo).into(imageView);

        infoText.setText(quizList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return quizList.size();
    }
}