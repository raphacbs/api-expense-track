package com.coelho.brasileiro.expensetrack.handle.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.model.StatusTransactionEnum;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.predicate.RecurringTransactionIsValid;
import com.coelho.brasileiro.expensetrack.repository.RecurringTransactionRepository;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.util.Constants;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.TransactionCodes.TRANSACTION_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.RecurringTransaction.RECURRING_TRANSACTION;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;

@Component
@AllArgsConstructor
public class CreateTransactionFromRecurringTransactionHandler extends AbstractHandler {
    private final RecurringTransactionRepository recurringTransactionRepository;
    private final TransactionRepository transactionRepository;
    private final Logger log = LoggerFactory.getLogger(CreateTransactionFromRecurringTransactionHandler.class);
    private final Converter mapper = Converter.INSTANCE;
    private final RecurringTransactionIsValid recurringTransactionIsValid;
    private List<Transaction> transactions;


    @Override
    protected void doHandle(Context context) {
        log.info("Iniciando consumo da mensagem do tópico a criar transactions");
        RecurringTransaction recurringTransaction = context.getEntity(RECURRING_TRANSACTION, RecurringTransaction.class);
        log.debug("Transação recorrente: {}", recurringTransaction);

        if(recurringTransactionIsValid.test(recurringTransaction)){
            log.info("Transação recorrente apta para ser processada: {}",recurringTransaction.getId());

            transactions = new ArrayList<>();
            for(int i= 1; i<= 6; i++){
                Transaction transaction = mapper.fromRecurringTransaction(recurringTransaction);
                transaction.setTotalValue(transaction.getTotalValue());
                transaction.setStatus(StatusTransactionEnum.SCHEDULED);
                transaction.setInstallments(1);
                transaction.setDueDate(transaction.getDueDate().plusMonths(i));

                if(this.transactionRepository.findByGroupIdAndDueDate(transaction.getGroupId(), transaction.getDueDate()).isEmpty()){
                    transactions.add(transaction);
                }

            }

            if(!transactions.isEmpty()){
                this.transactionRepository.saveAll(transactions);
                recurringTransaction.setLastProcessing(LocalDateTime.now());
                this.recurringTransactionRepository.save(recurringTransaction);
            }
        }
    }

}
