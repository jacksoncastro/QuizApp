package br.com.jackson.quizapp.model;

/**
 * Created by jackson on 30/11/16.
 */

public class Item {

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