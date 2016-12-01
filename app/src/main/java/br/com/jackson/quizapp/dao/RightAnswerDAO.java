package br.com.jackson.quizapp.dao;

import android.content.ContentValues;
import android.content.Context;

import br.com.jackson.quizapp.helper.MySQLiteHelper;
import br.com.jackson.quizapp.model.RightAnswer;

/**
 * Created by jackson on 30/11/16.
 */

public class RightAnswerDAO extends MySQLiteHelper {

    private final String TABLE_NAME = "right_answer";

    public RightAnswerDAO(Context context) {
        super(context);
    }

    public int insert(RightAnswer rightAnswer) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("quiz_id", rightAnswer.getQuizId());
        contentValues.put("item_id", rightAnswer.getItemId());
        return (int) getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }
}
