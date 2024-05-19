package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.repository.RecurringTransactionRepository;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.TransactionCodes.TRANSACTION_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;

@Component
public class SaveRecurringTransactionHandler extends AbstractHandler {
    private final RecurringTransactionRepository recurringTransactionRepository;
    private final TransactionRepository transactionRepository;
    private final Logger log = LoggerFactory.getLogger(SaveRecurringTransactionHandler.class);
    private final Converter mapper = Converter.INSTANCE;
    private Transaction transaction;

    public SaveRecurringTransactionHandler(RecurringTransactionRepository recurringTransactionRepository,
                                           TransactionRepository transactionRepository) {
        this.recurringTransactionRepository = recurringTransactionRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    protected void doHandle(Context context) {
        log.info("Iniciando consumo da mensagem do tópico");
        TransactionInput input = context.getInput(TRANSACTION_INPUT, TransactionInput.class);
        RecurringTransaction recurringTransaction = mapper.fromTransactionInput(input, getUser(input.getId()));
        recurringTransaction.setGroupId(transaction.getGroupId());
        log.info("Salvando recorrência da transação de id: {}", input.getId());
        recurringTransactionRepository.save(recurringTransaction);
        log.info("Recorrência de id: {} salva com sucesso!", recurringTransaction.getId());
    }

    private User getUser(String transactionId){
        transaction = transactionRepository.findById(UUID.fromString(transactionId)).orElseThrow(
                ()-> new BusinessException(TRANSACTION_NOT_FOUND)
        );
        return transaction.getUser();
    }

}
