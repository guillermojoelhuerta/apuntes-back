package com.apuntes.apuntes.exception;

public class ExceptionDetails {
    private String userMessage;
    private String seveerity;

    public ExceptionDetails(String userMessage, String seveerity) {
        super();
        this.userMessage = userMessage;
        this.seveerity = seveerity;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getSeveerity() {
        return seveerity;
    }

    public void setSeveerity(String seveerity) {
        this.seveerity = seveerity;
    }
}
