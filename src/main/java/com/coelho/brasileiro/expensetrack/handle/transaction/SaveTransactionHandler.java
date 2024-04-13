package com.coelho.brasileiro.expensetrack.handle.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Checks.isNotEmptyOrNotNull;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.*;

@Component

public class SaveTransactionHandler extends AbstractHandler {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final UserService userService;
    private List<Transaction> transactionsToSave;
    private Context context;

    public SaveTransactionHandler(TransactionRepository transactionRepository,
                                  TransactionService transactionService,
                                  UserService userService) {
        this.transactionRepository = transactionRepository;
        this.transactionService = transactionService;
        this.userService = userService;

    }

    @Override
    protected void doHandle(Context context) {
        this.transactionsToSave = new ArrayList<>();
        this.context = context;
        generateInstallmentTransactions();
        context.setEntities(TRANSACTIONS, transactionsToSave);
        saveAll();
    }

    private void saveAll(){
        this.transactionRepository.saveAll(transactionsToSave);
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

    private void generateInstallmentTransactions(){
        Transaction transaction = extractTransaction();
        prepareTransaction(transaction);
        transactionsToSave.add(transaction);
        Transaction nextInstallmentTransaction = this.transactionService.createNextInstallmentTransaction(transaction);
        for (int i = 0; i < transaction.getInstallments() - transaction.getCurrentInstallments(); i++) {
            transactionsToSave.add(nextInstallmentTransaction);
            nextInstallmentTransaction = this.transactionService.createNextInstallmentTransaction(nextInstallmentTransaction);
        }
    }

    
    private Transaction prepareTransaction(Transaction transaction) {
        transaction.setGroupId(UUID.randomUUID());
        transaction.setTotalValue(transaction.getValue() * transaction.getInstallments());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setIsRecurring(false);
        transaction.setIsDeleted(false);
        transaction.setUser(this.userService.getUserLogged());
        this.transactionService.setTransactionStatusByDate(transaction);
        return transaction;
    }


}
