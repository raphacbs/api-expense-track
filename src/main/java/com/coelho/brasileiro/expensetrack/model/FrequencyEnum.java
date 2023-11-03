package com.coelho.brasileiro.expensetrack.model;

public enum FrequencyEnum {
    ANNUAL("A"),
    MONTHLY("M"),
    BIWEEKLY("BW"),
    WEEKLY("W"),
    DAILY("D");

    private final String frequency;
    FrequencyEnum(String frequency) {
        this.frequency = frequency;
    }
}
