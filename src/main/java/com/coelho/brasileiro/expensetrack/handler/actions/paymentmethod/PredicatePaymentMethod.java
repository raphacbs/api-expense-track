package com.coelho.brasileiro.expensetrack.handler.actions.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import lombok.Builder;

import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotNull;

@Builder
public class PredicatePaymentMethod implements Predicate<Context> {
    @Override
    public boolean test(Context context) {
        return isNotNull(context.getParams().get("name")) ||
                isNotNull(context.getParams().get("description"));
    }


}
