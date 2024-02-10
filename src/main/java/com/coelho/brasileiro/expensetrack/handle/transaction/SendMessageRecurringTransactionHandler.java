package com.coelho.brasileiro.expensetrack.handle.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.service.KafkaProducerService;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import com.coelho.brasileiro.expensetrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotEmptyOrNotNull;
import static com.coelho.brasileiro.expensetrack.util.Constants.Topic.TOPIC_FREQUENCIA_MENSAL_A_PROCESSAR;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.*;

@Component
public class SendMessageRecurringTransactionHandler extends AbstractHandler {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final UserService userService;

    private Context context;
    private final KafkaProducerService<Transaction> producer;

    public SendMessageRecurringTransactionHandler(TransactionRepository transactionRepository,
                                                  TransactionService transactionService,
                                                  UserService userService,
                                                  KafkaProducerService<Transaction> producer) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.userService = userService;
        this.producer = producer;
    }


    @Override
    protected void doHandle(Context context) {
        this.context = context;
        if(isTransactionFrequent()){
           List<Transaction> transactions = context.getEntities(TRANSACTIONS, Transaction.class);
           transactions.forEach(transaction -> {
               producer.sendMessage(TOPIC_FREQUENCIA_MENSAL_A_PROCESSAR, transaction);
           });
        }
    }


    private boolean isTransactionFrequent() {
        TransactionInput input = extractTransactionInput();
        return isNotEmptyOrNotNull(input.getFrequency());
    }

    private Transaction extractTransaction() {
        return context.getEntity(TRANSACTION, Transaction.class);
    }

    private TransactionInput extractTransactionInput() {
        return context.getInput(TRANSACTION_INPUT, TransactionInput.class);
    }



}
