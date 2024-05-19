package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.CreateBudgetRecordsFromRecurringBudgetHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.stereotype.Component;

@Component
public class SchedulerCreateRecurringBudgetBuilder extends AFlowBuilder<SchedulerCreateRecurringBudgetBuilder> {

    private final FlowFactory flowFactory;
    private final InputValidator<BudgetInput> inputValidator;

    public SchedulerCreateRecurringBudgetBuilder(FlowFactory flowFactory, InputValidator<BudgetInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public SchedulerCreateRecurringBudgetBuilder create(Context context) {

        flow = flowFactory
                .start()
                .context(context)
                .addAction(CreateBudgetRecordsFromRecurringBudgetHandler.class)
                .build();
        return this;
    }
}
