package com.coelho.brasileiro.expensetrack;

import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class TestDataFactory {
    public Transaction createTransactionMonthly() {
        return Converter.INSTANCE.toEntity(createTransactionInputMonthly());
    }

    public TransactionInput createTransactionInputMonthly() {
        return TransactionInput.builder()
                .type("E")
                .description("Expense description")
                .value(52.0)
                .totalValue(52.0)
                .paymentDate(null)
                .dueDate(LocalDate.of(2024, 5, 1).toString())
                .installments(1)
                .currentInstallments(1)
                .merchant("Example Merchant")
                .tags("tag1,tag2,tag3")
                .categoryId("fb325baa-2da7-41e2-9d12-4f690f7d586c")
                .paymentMethodId("ca289287-fd6b-4202-9b8b-87e705633565")
                .budgetId(null)
                .moneyBoxId(null)
                .frequency("MONTHLY")
                .startDate(LocalDate.of(2024, 4, 1).toString())
                .endDate(LocalDate.of(2024, 12, 1).toString())
                .build();
    }
}
