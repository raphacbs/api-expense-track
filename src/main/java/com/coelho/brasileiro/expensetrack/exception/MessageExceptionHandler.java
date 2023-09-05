package com.coelho.brasileiro.expensetrack.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class MessageExceptionHandler {
    private Integer code;
    private String message;
}
