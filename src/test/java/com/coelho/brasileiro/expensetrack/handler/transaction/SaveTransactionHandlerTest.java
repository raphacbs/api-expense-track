package com.coelho.brasileiro.expensetrack.handler.transaction;

import com.coelho.brasileiro.expensetrack.TestDataFactory;
import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import com.coelho.brasileiro.expensetrack.repository.TransactionRepository;
import com.coelho.brasileiro.expensetrack.service.TransactionService;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION;
import static com.coelho.brasileiro.expensetrack.util.Constants.Transaction.TRANSACTION_INPUT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SaveTransactionHandlerTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    @InjectMocks
    private SaveTransactionHandler saveTransactionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveTransactionWhenInputIsValid() {
        // Given
        TransactionInput input = TestDataFactory.createTransactionInputMonthly();
        Transaction transaction = TestDataFactory.createTransactionMonthly();

        Context context = DefaultContext.builder().build();
        context.setInput(TRANSACTION_INPUT, input);

        context.setEntity(TRANSACTION, transaction);

        // When
        saveTransactionHandler.doHandle(context);

        // Then
        verify(transactionRepository, times(1)).saveAll(anyList());
    }

    @Test
    public void shouldGenerateInstallmentTransactionsWhenTransactionIsFrequent() {
        // Given
        TransactionInput input = TestDataFactory.createTransactionInputMonthly();
        Transaction transaction = TestDataFactory.createTransactionMonthly();

        Context context = DefaultContext.builder().build();
        context.setInput(TRANSACTION_INPUT, input);

        context.setEntity(TRANSACTION, transaction);

        // When
        saveTransactionHandler.doHandle(context);

        // Then
        verify(transactionService, times(1)).createNextInstallmentTransaction(any(Transaction.class));
    }
}