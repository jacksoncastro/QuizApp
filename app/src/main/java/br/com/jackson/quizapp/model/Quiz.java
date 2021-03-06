package br.com.jackson.quizapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18/11/16.
 */

public class Quiz implements Parcelable {

    private Integer id;

    private String question;

    private String imageLink;

    private List<Item> items;

    private int rightAnswer;

    public Quiz() {
    }

    public Quiz(String question, String imageLink, List<Item> items) {
        this.question = question;
        this.imageLink = imageLink;
        this.items = items;
    }

    public Quiz(Integer id, String question, String imageLink) {
        this.id = id;
        this.question = question;
        this.imageLink = imageLink;
    }

    protected Quiz(Parcel in) {
        ArrayList<Item> items = new ArrayList<>();
        in.readTypedList(items, Item.CREATOR);
        this.items = items;

        id = in.readInt();
        question = in.readString();
        imageLink = in.readString();
        rightAnswer = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(items);

        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(imageLink);
        dest.writeInt(rightAnswer);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}