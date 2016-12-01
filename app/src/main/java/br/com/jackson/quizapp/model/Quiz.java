package br.com.jackson.quizapp.model;

import java.util.List;

/**
 * Created by root on 18/11/16.
 */

public class Quiz {

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