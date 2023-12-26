package com.coelho.brasileiro.expensetrack.handle.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import com.coelho.brasileiro.expensetrack.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTIONS;

@Component
@AllArgsConstructor
public class SaveTransactionHandler extends AbstractHandler {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final UserService userService;

    @Override
    protected void doHandle(Context context) {
        Transaction transaction = context.getEntity(TRANSACTION, Transaction.class);
        saveTransaction(transaction, context);
    }

    private void saveTransaction(Transaction transaction, Context context) {
        transaction.setGroupId(UUID.randomUUID());
        transaction.setTotalValue(transaction.getValue() * transaction.getInstallments());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setIsRecurring(false);
        transaction.setIsDeleted(false);
        transaction.setUser(this.userService.getUserLogged());
        this.transactionService.setTransactionStatus(transaction);
        this.transactionRepository.save(transaction);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Transaction nextTransaction = this.transactionService.createNextTransaction(transaction);
        for (int i = 0; i < transaction.getInstallments() - transaction.getCurrentInstallments(); i++) {
            this.transactionRepository.save(nextTransaction);
            transactions.add(nextTransaction);
            nextTransaction = this.transactionService.createNextTransaction(nextTransaction);
        }
        context.setEntities(TRANSACTIONS, transactions);
    }


}
