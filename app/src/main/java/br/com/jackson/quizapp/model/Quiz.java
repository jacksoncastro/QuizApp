package br.com.jackson.quizapp.model;

/**
 * Created by root on 18/11/16.
 */

public class Quiz {

    private int imageId;

    private String question;

    private String[] itens;

    private int rightAnswer;

    public Quiz(int imageId, String question, String[] itens, int rightAnswer) {
        this.imageId = imageId;
        this.question = question;
        this.itens = itens;
        this.rightAnswer = rightAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String[] getItens() {
        return itens;
    }

    public void setItens(String[] itens) {
        this.itens = itens;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }
}