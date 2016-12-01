package br.com.jackson.quizapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import org.apache.commons.io.IOUtils;

import java.util.List;

import br.com.jackson.quizapp.helper.MySQLiteHelper;
import br.com.jackson.quizapp.model.Quiz;

/**
 * Created by jackson on 30/11/16.
 */

public class QuizDAO extends MySQLiteHelper {

    private final String TABLE_NAME = "quiz";

    public QuizDAO(Context context) {
        super(context);
    }

    public int countAll () {
        return (int) DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_NAME);
    }

    public Quiz findById(int quizId) {
        String[] columns = {"id", "question", "image_link"};
        String selection = "id=?";
        String[] selectionArgs = {String.valueOf(quizId)};
        String orderBy = "id";
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String question = cursor.getString(cursor.getColumnIndex("question"));
                String imageLink = cursor.getString(cursor.getColumnIndex("image_link"));

                return new Quiz(id, question, imageLink);
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return null;
    }

    public int insert (Quiz quiz) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("question", quiz.getQuestion());
        contentValues.put("image_link", quiz.getImageLink());
        return (int) getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }
}