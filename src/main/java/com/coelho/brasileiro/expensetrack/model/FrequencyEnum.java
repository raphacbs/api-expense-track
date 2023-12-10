package com.coelho.brasileiro.expensetrack.model;

public enum FrequencyEnum {
    ANNUAL("ANNUAL"),
    MONTHLY("MONTHLY"),
    BIWEEKLY("BIWEEKLY"),
    WEEKLY("WEEKLY"),
    DAILY("DAILY");

    private final String frequency;
    FrequencyEnum(String frequency) {
        this.frequency = frequency;
    }
}
