package com.coelho.brasileiro.expensetrack.predicate;

import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;

import java.time.LocalDate;
import java.util.function.Predicate;

public class RecurringTransactionIsValid implements Predicate<RecurringTransaction> {
    @Override
    public boolean test(RecurringTransaction recurringTransaction) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = recurringTransaction.getStartDate();
        LocalDate endDate = recurringTransaction.getEndDate();
        LocalDate lastProcessing = recurringTransaction.getLastProcessing().toLocalDate();

        return recurringTransaction.getIsActive() &&
                !recurringTransaction.getIsDeleted() &&
                !today.isEqual(lastProcessing) &&
                 today.isAfter(lastProcessing) &&
                (endDate) != null ?
                (today.isAfter(startDate) || today.isEqual(startDate)) && (today.isEqual(endDate) || today.isBefore(endDate))
                        : (today.isAfter(startDate) || today.isEqual(startDate));
    }
}
