package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.MoneyBox;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.model.TransactionMoneyBox;
import com.coelho.brasileiro.expensetrack.predicate.IsNotEmptyOrNotNullPredicate;
import com.coelho.brasileiro.expensetrack.repository.TransactionMoneyBoxRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTIONS;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;

@Component

public class SaveTransactionMoneyBoxHandler extends AbstractHandler {

    private final IsNotEmptyOrNotNullPredicate isNotEmptyOrNotNullPredicate;
    private final TransactionMoneyBoxRepository transactionMoneyBoxRepository;

    public SaveTransactionMoneyBoxHandler(TransactionMoneyBoxRepository transactionMoneyBoxRepository) {
        this.isNotEmptyOrNotNullPredicate = new IsNotEmptyOrNotNullPredicate();
        this.transactionMoneyBoxRepository = transactionMoneyBoxRepository;
    }

    @Override
    protected void doHandle(Context context) {
        TransactionInput input = context.getInput(TRANSACTION_INPUT, TransactionInput.class);
        if(isNotEmptyOrNotNullPredicate.test(input.getMoneyBoxId())){
            List<Transaction> transactions = context.getEntities(TRANSACTIONS, Transaction.class);
            for(Transaction transaction : transactions){
                TransactionMoneyBox transactionMoneyBox = TransactionMoneyBox
                        .builder()
                        .moneyBox(MoneyBox.builder().id(UUID.fromString(input.getMoneyBoxId())).build())
                        .transaction(transaction)
                        .build();
                transactionMoneyBoxRepository.save(transactionMoneyBox);
            }
        }
    }



}
