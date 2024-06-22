package com.coelho.brasileiro.expensetrack.controller;

import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.exception.MessageExceptionHandler;
import com.coelho.brasileiro.expensetrack.exception.ResourceValidationException;
import com.coelho.brasileiro.expensetrack.exception.TokenException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.ValidationCodes.VALIDATION_PARAMS;

@org.springframework.web.bind.annotation.ControllerAdvice(basePackages = {"com.coelho.brasileiro.expensetrack.controller",
        "com.coelho.brasileiro.expensetrack.filter", "com.coelho.brasileiro.expensetrack.handle.actions"})
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
                businessException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ResourceValidationException.class)
    public ResponseEntity<MessageExceptionHandler> errorValidation(ResourceValidationException resourceValidationException) {
        MessageExceptionHandler error = new MessageExceptionHandler(VALIDATION_PARAMS,
                resourceValidationException.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageExceptionHandler> error(RuntimeException businessException) {
        logger.error("Error", businessException);
        MessageExceptionHandler error = new MessageExceptionHandler(500,
                "Internal Server Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageExceptionHandler> error2(Exception businessException) {
        MessageExceptionHandler error = new MessageExceptionHandler(500,
                "Internal Server Error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
