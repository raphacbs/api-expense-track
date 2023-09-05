package com.coelho.brasileiro.expensetrack.exception;

public class ResourceValidationException extends RuntimeException {

    public ResourceValidationException(String message){
        super(message);
    }

    public ResourceValidationException(String message, Throwable throwable){
        super(message, throwable);
    }
}
