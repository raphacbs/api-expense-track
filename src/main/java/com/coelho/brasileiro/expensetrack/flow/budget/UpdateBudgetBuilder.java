package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.*;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateBudgetBuilder extends AFlowBuilder<UpdateBudgetBuilder> {

    private final FlowFactory  flowFactory;
    private final InputValidator<BudgetInput> inputValidator;
    @Autowired
    public UpdateBudgetBuilder(FlowFactory flowFactory, InputValidator<BudgetInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public UpdateBudgetBuilder create(Context context) {
        ValidateInput<BudgetInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertBudgetInputToBudgetHandler.class)
                .addAction(VerifyIfBudgetNotExistHandler.class)
                .addAction(UpdateBudgetInputToBudgetHandler.class)
                .addAction(SaveWithRecurringBudgetHandler.class)
                .addAction(ConvertBudgetToBudgetDtoHandler.class)
                .build();
        return this;
    }
}
