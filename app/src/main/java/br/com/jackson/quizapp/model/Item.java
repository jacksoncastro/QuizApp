package br.com.jackson.quizapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jackson on 30/11/16.
 */

public class Item implements Parcelable {

    private int id;

    private String answer;

    private int quizId;

    public Item() {
    }

    public Item(String answer) {
        this.answer = answer;
    }

    public Item(int id, String answer) {
        this.id = id;
        this.answer = answer;
    }

    public Item(String answer, int quizId) {
        this.answer = answer;
        this.quizId = quizId;
    }

    protected Item(Parcel in) {
        id = in.readInt();
        answer = in.readString();
        quizId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(answer);
        dest.writeInt(quizId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }
}