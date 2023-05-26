package com.apuntes.apuntes.exception;

public class HandleException extends Throwable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HandleException(String message) {
        super();
        this.message = message;
    }
}
