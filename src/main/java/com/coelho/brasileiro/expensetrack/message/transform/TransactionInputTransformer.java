package com.coelho.brasileiro.expensetrack.message.transform;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class TransactionInputTransformer
        extends DefaultTransform
        implements Transform<TransactionInput> {

    public TransactionInputTransformer(byte[] data) throws IOException {
        super(data);
    }

    @Override
    public TransactionInput toObject() throws JsonProcessingException {
        return getObjectMapper().treeToValue(getNode(), TransactionInput.class);
    }
}
