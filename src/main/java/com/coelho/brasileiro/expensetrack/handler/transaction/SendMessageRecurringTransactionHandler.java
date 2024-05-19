package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.config.PropertiesConfig;
import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.message.KafkaProducerService;
import com.coelho.brasileiro.expensetrack.model.FrequencyEnum;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotEmptyOrNotNull;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTIONS;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;

@Component
public class SendMessageRecurringTransactionHandler extends AbstractHandler {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final UserService userService;

    private Context context;
    private final KafkaProducerService<TransactionInput> producer;
    private final PropertiesConfig propertiesConfig;
    private final Logger log = LoggerFactory.getLogger(SendMessageRecurringTransactionHandler.class);

    public SendMessageRecurringTransactionHandler(TransactionRepository transactionRepository,
                                                  TransactionService transactionService,
                                                  UserService userService,
                                                  KafkaProducerService<TransactionInput> producer,
                                                  PropertiesConfig propertiesConfig) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.userService = userService;
        this.producer = producer;
        this.propertiesConfig = propertiesConfig;
    }


    @Override
    protected void doHandle(Context context) {
        this.context = context;
        TransactionInput transactionInput = extractTransactionInput();
        List<Transaction> transactions = context.getEntities(TRANSACTIONS, Transaction.class);
        if(transactions.stream().findFirst().isPresent() && isTransactionFrequent()){
            transactionInput.setId(transactions.get(0).getId().toString());
            producer.sendMessage(propertiesConfig.getTopicTransacaoMensalASalvar(), transactionInput);
        }else{
            log.warn("Não foi encontrada transação para enviar para a recorrência");
        }
    }
    private boolean isTransactionFrequent() {
        TransactionInput input = extractTransactionInput();
        return isNotEmptyOrNotNull(input.getFrequency()) && input.getFrequency().equalsIgnoreCase(FrequencyEnum.MONTHLY.name());
    }
    private TransactionInput extractTransactionInput() {
        return context.getInput(TRANSACTION_INPUT, TransactionInput.class);
    }
}
