package br.com.jackson.quizapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.jackson.quizapp.helper.MySQLiteHelper;
import br.com.jackson.quizapp.model.Item;

/**
 * Created by jackson on 30/11/16.
 */

public class ItemDAO extends MySQLiteHelper {

    private final String TABLE_NAME = "item";

    public ItemDAO(Context context) {
        super(context);
    }

    public List<Item> findItemsByQuiz(int quizId) {
        String[] columns = {"id", "answer"};
        String selection = "quiz_id=?";
        String[] selectionArgs = {String.valueOf(quizId)};
        String orderBy = "id";
        Cursor cursor = getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);

        List<Item> items = new ArrayList<>();

        try {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String answer = cursor.getString(cursor.getColumnIndex("answer"));

                items.add(new Item(id, answer));
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return items;
    }


    /**
     * Insert an Item and return your id
     *
     * @param item
     * @return
     */
    public int insert(Item item) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("answer", item.getAnswer());
        contentValues.put("quiz_id", item.getQuizId());
        return (int) getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }
}