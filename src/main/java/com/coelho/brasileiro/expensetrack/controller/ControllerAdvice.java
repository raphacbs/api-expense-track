package com.coelho.brasileiro.expensetrack.controller;

import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.exception.MessageExceptionHandler;
import com.coelho.brasileiro.expensetrack.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {"com.coelho.brasileiro.expensetrack.controller",
        "com.coelho.brasileiro.expensetrack.filter"})
public class ControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

    @ResponseBody
    @ExceptionHandler(TokenException.class)
    public ResponseEntity<MessageExceptionHandler> token(TokenException tokenException) {
        MessageExceptionHandler error = new MessageExceptionHandler(HttpStatus.UNAUTHORIZED.value(),
                tokenException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<MessageExceptionHandler> business(BusinessException businessException) {
        MessageExceptionHandler error = new MessageExceptionHandler(businessException.getErrorCode(),
                "There is something wrong with your request.");
        return new ResponseEntity<>(error, HttpStatus.ACCEPTED);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageExceptionHandler> error(BusinessException businessException) {
        MessageExceptionHandler error = new MessageExceptionHandler(businessException.getErrorCode(),
                "There is something wrong with your request.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> error2(BusinessException businessException) {
        MessageExceptionHandler error = new MessageExceptionHandler(businessException.getErrorCode(),
                "There is something wrong with your request.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
