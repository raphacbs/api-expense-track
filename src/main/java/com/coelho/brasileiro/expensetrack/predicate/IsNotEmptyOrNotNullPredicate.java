package com.coelho.brasileiro.expensetrack.predicate;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;

import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotEmptyOrNotNull;

public class IsNotEmptyOrNotNullPredicate implements Predicate<String> {
    @Override
    public boolean test(String input) {
        return isNotEmptyOrNotNull(input);
    }
}
