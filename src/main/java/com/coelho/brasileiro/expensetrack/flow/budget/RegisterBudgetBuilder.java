package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.ConvertBudgetInputToBudgetHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.ConvertBudgetToBudgetDtoHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.SaveBudgetHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.SaveWithRecurringBudgetHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.stereotype.Component;

@Component
public class RegisterBudgetBuilder extends AFlowBuilder<RegisterBudgetBuilder> {

    private final FlowFactory flowFactory;
    private final InputValidator<BudgetInput> inputValidator;

    public RegisterBudgetBuilder(FlowFactory flowFactory, InputValidator<BudgetInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public RegisterBudgetBuilder create(Context context) {
        ValidateInput<BudgetInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertBudgetInputToBudgetHandler.class)
                .addAction(SaveWithRecurringBudgetHandler.class)
                .addAction(ConvertBudgetToBudgetDtoHandler.class)
                .build();
        return this;
    }
}
