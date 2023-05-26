package com.apuntes.apuntes.exception;

public class NameException extends RuntimeException{

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NameException(String message) {
        super();
        this.message = message;
    }
}
