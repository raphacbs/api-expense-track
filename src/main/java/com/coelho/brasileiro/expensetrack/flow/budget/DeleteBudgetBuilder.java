package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.ConvertBudgetInputToBudgetHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.DeleteBudgetHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.VerifyIfBudgetNotExistHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteBudgetBuilder extends AFlowBuilder<DeleteBudgetBuilder> {

    private final FlowFactory  flowFactory;
    @Autowired
    public DeleteBudgetBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public DeleteBudgetBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(ConvertBudgetInputToBudgetHandler.class)
                .addAction(VerifyIfBudgetNotExistHandler.class)
                .addAction(DeleteBudgetHandler.class)
                .build();
        return this;
    }
}
