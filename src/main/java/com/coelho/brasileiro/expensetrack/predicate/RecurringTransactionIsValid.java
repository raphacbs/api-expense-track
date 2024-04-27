package com.coelho.brasileiro.expensetrack.predicate;

import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;

import java.time.LocalDate;
import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotNull;
import static com.coelho.brasileiro.expensetrack.util.Checks.isNull;

public class RecurringTransactionIsValid implements Predicate<RecurringTransaction> {
    @Override
    public boolean test(RecurringTransaction recurringTransaction) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = recurringTransaction.getStartDate();
        LocalDate endDate = recurringTransaction.getEndDate();
        LocalDate lastProcessing = isNotNull(recurringTransaction.getLastProcessing()) ? recurringTransaction.getLastProcessing().toLocalDate() : null;

        return recurringTransaction.getIsActive() &&
                !recurringTransaction.getIsDeleted() &&
                (isNull(lastProcessing) || !today.isEqual(lastProcessing) || today.isAfter(lastProcessing)) &&
                (endDate) != null ?
                (startDate.isBefore(today) || today.isEqual(startDate)) && ( endDate.isAfter(today) ||today.isEqual(endDate))
                        : (startDate.isBefore(today) || today.isEqual(startDate));
    }
}
