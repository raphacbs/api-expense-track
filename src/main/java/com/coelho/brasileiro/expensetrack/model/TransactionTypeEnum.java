package com.coelho.brasileiro.expensetrack.model;

public enum TransactionTypeEnum {
    E("EXPENSE"), R("REVENUE");
    public String type;

    TransactionTypeEnum(String type) {
        this.type = type;
    }
}
