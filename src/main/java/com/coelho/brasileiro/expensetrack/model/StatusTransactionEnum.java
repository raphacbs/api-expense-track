package com.coelho.brasileiro.expensetrack.model;

public enum StatusTransactionEnum {
    PAID("PAID"),
    LATE_PAYMENT("LATE_PAYMENT"),
    SCHEDULED("SCHEDULED"),
    CANCELED("CANCELED");


    private final String value;

    StatusTransactionEnum(String value) {
        this.value = value;
    }

}
