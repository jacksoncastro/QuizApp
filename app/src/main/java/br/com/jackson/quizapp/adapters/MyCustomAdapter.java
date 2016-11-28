package br.com.jackson.quizapp.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.R;

public class MyCustomAdapter extends BaseAdapter implements ListAdapter {
    private List<String> list = new ArrayList<>();
    private Context context;
    private RadioButton mSelectedRB;
    private int mSelectedPosition = -1;

    public MyCustomAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
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
        radioButton.setText(list.get(position));

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something
                list.remove(position); //or some other task
                notifyDataSetChanged();
            }
        });

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventRadioList(view, position);
            }
        });


        if (mSelectedPosition != position) {
            radioButton.setChecked(false);
        } else {
            radioButton.setChecked(true);
            if (mSelectedRB != null && radioButton != mSelectedRB) {
                mSelectedRB = radioButton;
            }
        }

//        addBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                //do something
//                notifyDataSetChanged();
//            }
//        });

        return view;
    }


    private void eventRadioList(View view, int position) {
        if(position != mSelectedPosition && mSelectedRB != null){
            mSelectedRB.setChecked(false);
        }

        mSelectedPosition = position;
        mSelectedRB = (RadioButton) view;
    }
}