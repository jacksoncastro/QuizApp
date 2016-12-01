package br.com.jackson.quizapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.R;
import br.com.jackson.quizapp.model.Item;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private List<Item> answerList = new ArrayList<>();
    private Context context;
    private RadioButton selectedRadioButton;
    private int selectedPositionRadioButton = -1;

    public MyCustomAdapter(List<Item> answerList, Context context) {
        this.answerList = answerList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answerList.size();
    }

    @Override
    public Object getItem(int pos) {
        return answerList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
//        return list.get(pos).getId();
//        just return 0 if your list items do not have an Id variable.
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_form_quiz, null);
        }

        //Handle buttons and add onClickListeners
        Button deleteBtn = (Button) view.findViewById(R.id.delete_btn);

        RadioButton radioButton = (RadioButton) view.findViewById(R.id.list_item_radio_button);
        radioButton.setText(answerList.get(position).getAnswer());

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                answerList.remove(position); //or some other task
                selectedRadioButton = null;
                selectedPositionRadioButton = -1;
                notifyDataSetChanged();
            }
        });

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventRadioList(view, position);
            }
        });


        if (selectedPositionRadioButton != position) {
            radioButton.setChecked(false);
        } else {
            radioButton.setChecked(true);
            if (selectedRadioButton != null && radioButton != selectedRadioButton) {
                selectedRadioButton = radioButton;
            }
        }
        return view;
    }


    private void eventRadioList(View view, int position) {
        if (position != selectedPositionRadioButton && selectedRadioButton != null) {
            selectedRadioButton.setChecked(false);
        }

        selectedPositionRadioButton = position;
        selectedRadioButton = (RadioButton) view;
    }

    public RadioButton getSelectedRadioButton() {
        return selectedRadioButton;
    }

    public int getSelectedPositionRadioButton() {
        return selectedPositionRadioButton;
    }

    public List<Item> getAnswerList() {
        return answerList;
    }
}