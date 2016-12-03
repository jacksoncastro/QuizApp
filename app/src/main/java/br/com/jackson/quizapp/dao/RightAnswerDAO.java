package br.com.jackson.quizapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

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


    public int getRightItemIdQuiz(int quizId) {
        String[] columns = {"item_id"};
        String selection = "quiz_id=?";
        String[] selectionArgs = {String.valueOf(quizId)};
        Cursor cursor = getWritableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        try {
            while (cursor.moveToNext()) {
                return cursor.getInt(cursor.getColumnIndex("item_id"));
            }
            return 0;
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
    }
}
