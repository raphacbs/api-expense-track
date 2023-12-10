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
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION;

@Component
@AllArgsConstructor
public class SaveTransactionBudgetHandler extends AbstractHandler {
    private final TransactionRepository transactionRepository;
    private final TransactionService transactionService;
    private final UserService userService;

    @Override
    protected void doHandle(Context context) {
        Transaction transaction = context.getEntity(TRANSACTION, Transaction.class);
        saveTransaction(transaction);
    }

    private void saveTransaction(Transaction transaction) {
        transaction.setGroupId(UUID.randomUUID());
        transaction.setTotalValue(transaction.getValue() * transaction.getInstallments());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setIsRecurring(false);
        transaction.setIsDeleted(false);
        transaction.setUser(this.userService.getUserLogged());
        this.transactionService.setTransactionStatus(transaction);
        this.transactionRepository.save(transaction);

        Transaction nextTransaction = this.transactionService.createNextTransaction(transaction);
        for (int i = 0; i < transaction.getInstallments() - transaction.getCurrentInstallments(); i++) {
            this.transactionRepository.save(nextTransaction);
            nextTransaction = this.transactionService.createNextTransaction(nextTransaction);
        }
    }


}
