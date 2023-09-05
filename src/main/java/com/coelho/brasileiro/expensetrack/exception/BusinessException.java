package com.coelho.brasileiro.expensetrack.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private String message;
    private Integer errorCode;

    public BusinessException(Integer errorCode){
        this.errorCode = errorCode;
    }

}
