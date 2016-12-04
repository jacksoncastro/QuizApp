package br.com.jackson.quizapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.helper.MySQLiteHelper;
import br.com.jackson.quizapp.model.Quiz;

/**
 * Created by jackson on 30/11/16.
 */

public class QuizDAO extends MySQLiteHelper {

    private final String TABLE_NAME = "quiz";
    private final String COLUMN_ID = "id";
    private final String COLUMN_QUESTION = "question";
    private final String COLUMN_IMAGE_LINK = "image_link";

    public QuizDAO(Context context) {
        super(context);
    }

    public int countAll () {
        return (int) DatabaseUtils.queryNumEntries(getReadableDatabase(), TABLE_NAME);
    }


    public List<Quiz> findAll() {
        String[] columns = {COLUMN_ID, COLUMN_QUESTION, COLUMN_IMAGE_LINK};
        String orderBy = COLUMN_QUESTION;

        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, null, null, null, null, orderBy);

        List<Quiz> quizList = new ArrayList<>();

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
                String imageLink = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_LINK));

                quizList.add(new Quiz(id, question, imageLink));
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return quizList;
    }

    public List<Quiz> findAllRandomWithLimit(int limit) {

        String sql = "SELECT %s, %s, %s FROM %s GROUP BY 1,2,3 ORDER BY RANDOM() LIMIT %d;";
        sql = String.format(sql, COLUMN_ID, COLUMN_QUESTION, COLUMN_IMAGE_LINK, TABLE_NAME, limit);

        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        List<Quiz> quizList = new ArrayList<>();

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String question = cursor.getString(cursor.getColumnIndex(COLUMN_QUESTION));
                String imageLink = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_LINK));

                quizList.add(new Quiz(id, question, imageLink));
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return quizList;
    }

    public int insert (Quiz quiz) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_QUESTION, quiz.getQuestion());
        contentValues.put(COLUMN_IMAGE_LINK, quiz.getImageLink());
        return (int) getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }
}