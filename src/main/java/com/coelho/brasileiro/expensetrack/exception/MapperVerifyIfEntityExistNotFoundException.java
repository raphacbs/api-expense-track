package com.coelho.brasileiro.expensetrack.exception;

public class MapperVerifyIfEntityExistNotFoundException extends RuntimeException {

    public MapperVerifyIfEntityExistNotFoundException(String message){
        super(message);
    }

    public MapperVerifyIfEntityExistNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
