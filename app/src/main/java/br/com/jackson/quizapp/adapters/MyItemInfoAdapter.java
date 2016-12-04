package br.com.jackson.quizapp.adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jackson.quizapp.R;
import br.com.jackson.quizapp.model.Item;

public class MyItemInfoAdapter extends RecyclerView.Adapter<MyItemInfoAdapter.ViewHolder> {

    private List<Item> items;
    private int rightItemIdQuiz;

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

    public MyItemInfoAdapter(List<Item> items, int rightItemIdQuiz) {
        this.items = items;
        this.rightItemIdQuiz = rightItemIdQuiz;
    }

    @Override
    public MyItemInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_question_info, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Item item = items.get(position);

        TextView infoText = (TextView) holder.view.findViewById(R.id.question_info_text);

        if (item.getId() == rightItemIdQuiz) {
            infoText.setTextColor(Color.BLUE);
        }

        infoText.setText(item.getAnswer());
    }



    @Override
    public int getItemCount() {
        return items.size();
    }
}