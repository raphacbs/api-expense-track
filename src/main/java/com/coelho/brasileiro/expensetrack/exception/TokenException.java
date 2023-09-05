package com.coelho.brasileiro.expensetrack.exception;

public class TokenException extends RuntimeException{
    public TokenException(String message){
        super(message);
    }
    public TokenException(Throwable e){
        super(e);
    }



    public TokenException(String message, Throwable throwable){
        super(message, throwable);
    }
}
