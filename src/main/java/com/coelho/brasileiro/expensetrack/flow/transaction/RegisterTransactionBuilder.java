package com.coelho.brasileiro.expensetrack.flow.transaction;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ConvertEntityToDtoHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.ConvertInputToEntityHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.transaction.SaveTransactionBudgetHandler;
import com.coelho.brasileiro.expensetrack.handle.transaction.SaveTransactionHandler;
import com.coelho.brasileiro.expensetrack.handle.transaction.SaveTransactionMoneyBoxHandler;
import com.coelho.brasileiro.expensetrack.handle.transaction.SendMessageRecurringTransactionHandler;
import com.coelho.brasileiro.expensetrack.input.TransactionInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import jakarta.inject.Named;
import org.springframework.stereotype.Component;

@Component
@Named("registerTransactionBuilder")
public class RegisterTransactionBuilder extends AFlowBuilder<RegisterTransactionBuilder> {

    private final FlowFactory flowFactory;
    private final InputValidator<TransactionInput> inputValidator;

    public RegisterTransactionBuilder(FlowFactory flowFactory, InputValidator<TransactionInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public RegisterTransactionBuilder create(Context context) {
        ValidateInput<TransactionInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertInputToEntityHandler.class)
                .addAction(SaveTransactionHandler.class)
                .addAction(SendMessageRecurringTransactionHandler.class)
                .addAction(SaveTransactionBudgetHandler.class)
                .addAction(SaveTransactionMoneyBoxHandler.class)
                .addAction(ConvertEntityToDtoHandler.class)
                .build();

        return this;
    }
}
