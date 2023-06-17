package com.apuntes.apuntes.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String message;

    public ResourceNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
