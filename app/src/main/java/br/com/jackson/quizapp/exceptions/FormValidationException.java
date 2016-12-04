package br.com.jackson.quizapp.exceptions;

/**
 * Created by jackson on 03/12/16.
 */

public class FormValidationException extends Exception {

    private int messageId;

    public FormValidationException(int messageId) {
        super("Error to valid form");
        this.messageId = messageId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}