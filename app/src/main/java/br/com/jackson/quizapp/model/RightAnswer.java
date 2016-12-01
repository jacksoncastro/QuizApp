package br.com.jackson.quizapp.model;

/**
 * Created by jackson on 30/11/16.
 */

public class RightAnswer {

    private int id;

    private int quizId;

    private int itemId;

    public RightAnswer(int quizId, int itemId) {
        this.quizId = quizId;
        this.itemId = itemId;
    }

    public RightAnswer(int id, int quizId, int itemId) {
        this.id = id;
        this.quizId = quizId;
        this.itemId = itemId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}