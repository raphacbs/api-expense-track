package com.coelho.brasileiro.expensetrack.exception;

public class MapperRegistryNotFoundException extends RuntimeException {

    public MapperRegistryNotFoundException(String message){
        super(message);
    }

    public MapperRegistryNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
