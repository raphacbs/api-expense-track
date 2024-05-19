package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.model.TransactionBudget;
import com.coelho.brasileiro.expensetrack.predicate.TransactionBudgetPredicate;
import com.coelho.brasileiro.expensetrack.repository.TransactionBudgetRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTIONS;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;

@Component

public class SaveTransactionBudgetHandler extends AbstractHandler {

    private final TransactionBudgetPredicate  transactionBudgetPredicate;
    private final TransactionBudgetRepository transactionBudgetRepository;

    public SaveTransactionBudgetHandler(TransactionBudgetRepository transactionBudgetRepository) {
        this.transactionBudgetPredicate = new TransactionBudgetPredicate();
        this.transactionBudgetRepository = transactionBudgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        TransactionInput input = context.getInput(TRANSACTION_INPUT, TransactionInput.class);
        if(transactionBudgetPredicate.test(input)){
            List<Transaction> transactions = context.getEntities(TRANSACTIONS, Transaction.class);
            for(Transaction transaction : transactions){
                TransactionBudget transactionBudget = TransactionBudget
                        .builder()
                        .budget(Budget.builder().id(UUID.fromString(input.getBudgetId())).build())
                        .transaction(transaction)
                        .build();
                transactionBudgetRepository.save(transactionBudget);
            }
        }
    }



}
