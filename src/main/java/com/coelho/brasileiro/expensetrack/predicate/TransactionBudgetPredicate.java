package com.coelho.brasileiro.expensetrack.predicate;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;

import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotEmptyOrNotNull;

public class TransactionBudgetPredicate implements Predicate<TransactionInput> {
    @Override
    public boolean test(TransactionInput input) {
        return isNotEmptyOrNotNull(input.getBudgetId());
    }
}
