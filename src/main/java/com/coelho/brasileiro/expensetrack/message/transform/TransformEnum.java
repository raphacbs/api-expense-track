package com.coelho.brasileiro.expensetrack.message.transform;


import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;

public enum TransformEnum {
    RECURRING_TRANSACTION(RecurringTransaction.class),
    TRANSACTION_INPUT(TransactionInput.class);
    private Class<?> type;

    TransformEnum(Class<?> type) {
        this.type = type;
    }

    public static TransformEnum fromType(Class<?> type){
        for(TransformEnum value : TransformEnum.values()){
            if (value.type.equals(type)) {
                return value;
            }
        }
        throw new RuntimeException("TransformEnum not found");
    }
}

