package com.coelho.brasileiro.expensetrack.message.transform;

import com.coelho.brasileiro.expensetrack.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotNullAndNotEqual;

public class RecurringTransactionTransformer
        extends DefaultTransform
        implements Transform<RecurringTransaction> {

    public RecurringTransactionTransformer(byte[] data) throws IOException {
        super(data);
    }

    @Override
    public RecurringTransaction toObject() throws JsonProcessingException {
        PaymentMethod paymentMethod = PaymentMethod.builder()
                .id(UUID.fromString(getPropertyValue("payment_method").asText()))
                .build();
        User user = User.builder()
                .id(UUID.fromString(getPropertyValue("user_id").asText()))
                .build();

        Category category = null;
        MoneyBox moneyBox = null;
        Budget budget = null;

        if (isNotNullAndNotEqual(getPropertyValue("category_id").asText(), "null"))
            category = Category.builder()
                    .id(UUID.fromString(getPropertyValue("category_id").asText()))
                    .build();


        if (isNotNullAndNotEqual(getPropertyValue("budget_id").asText(), "null")) {
            budget = Budget.builder().id(UUID.fromString(getPropertyValue("budget_id").asText()))
                    .build();
        }

        if (isNotNullAndNotEqual(getPropertyValue("money_box_id").asText(), "null")) {
            moneyBox = MoneyBox.builder().id(UUID.fromString(getPropertyValue("money_box_id").asText()))
                    .build();
        }
        ((ObjectNode) getNode()).putPOJO("payment_method", paymentMethod);
        ((ObjectNode) getNode()).putPOJO("user_id", user);
        ((ObjectNode) getNode()).putPOJO("category_id", category);
        ((ObjectNode) getNode()).putPOJO("money_box_id", moneyBox);
        ((ObjectNode) getNode()).putPOJO("budget_id", budget);

        return getObjectMapper().treeToValue(getNode(), RecurringTransaction.class);
    }
}
