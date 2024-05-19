package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.predicate.RecurringTransactionIsValid;
import com.coelho.brasileiro.expensetrack.repository.RecurringTransactionRepository;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Constants.RecurringTransaction.RECURRING_TRANSACTION;

@Component
@AllArgsConstructor
public class CreateTransactionFromRecurringTransactionHandler extends AbstractHandler {
    private final RecurringTransactionRepository recurringTransactionRepository;
    private final TransactionRepository transactionRepository;
    private final Logger log = LoggerFactory.getLogger(CreateTransactionFromRecurringTransactionHandler.class);
    private final Converter mapper = Converter.INSTANCE;

    @Override
    protected void doHandle(Context context) {
        log.info("Iniciando consumo da mensagem do tópico a criar transactions");
        Predicate<RecurringTransaction> recurringTransactionIsValid = new RecurringTransactionIsValid();
        RecurringTransaction recurringTransaction = context.getEntity(RECURRING_TRANSACTION, RecurringTransaction.class);
        log.debug("Transação recorrente: {}", recurringTransaction);

        if (recurringTransactionIsValid.test(recurringTransaction)) {
            log.info("Transação recorrente apta para ser processada: {}", recurringTransaction.getId());

            List<Transaction> transactions = new ArrayList<>();
            for (int i = 1; i <= 12; i++) {
                Transaction transaction = mapper.fromRecurringTransaction(recurringTransaction);
                transaction.setTotalValue(transaction.getValue());
                transaction.setStatus(StatusTransactionEnum.SCHEDULED);
                transaction.setInstallments(1);
                transaction.setCurrentInstallments(1);
                transaction.setDueDate(transaction.getDueDate().plusMonths(i));
                transaction.setCreatedAt(LocalDateTime.now());

                if (this.transactionRepository.findByGroupIdAndDueDate(transaction.getGroupId(), transaction.getDueDate()).isEmpty()) {
                    transactions.add(transaction);
                }
            }

            if (!transactions.isEmpty()) {
                this.transactionRepository.saveAll(transactions);
                recurringTransaction.setLastProcessing(LocalDateTime.now());
                this.recurringTransactionRepository.save(recurringTransaction);
            }
        }
    }

}
