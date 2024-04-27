package com.coelho.brasileiro.expensetrack.message.transform;

import java.io.IOException;

public class TransformFactory<T> {
    private TransformFactory(){

    }

    public static Transform create(TransformEnum transformEnum, byte[] data) throws IOException {
        return switch (transformEnum) {
            case TRANSACTION_INPUT -> new TransactionInputTransformer(data);
            case RECURRING_TRANSACTION -> new RecurringTransactionTransformer(data);
            default -> throw new IllegalStateException("Unexpected value: " + transformEnum);
        };
    }
}
