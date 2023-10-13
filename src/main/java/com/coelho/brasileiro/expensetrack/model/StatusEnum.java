package com.coelho.brasileiro.expensetrack.model;

public enum StatusEnum {
    IN_PROGRESS("I"),
    DONE("D"),
    SCHEDULED("S"),
    CANCELED("C");


    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }

}
