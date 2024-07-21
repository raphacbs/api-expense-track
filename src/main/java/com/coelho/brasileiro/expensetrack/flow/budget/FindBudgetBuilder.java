package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.ConvertBudgePageToResponseBudgetHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.GetBudgetParamsHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.SetTotalSpentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindBudgetBuilder extends AFlowBuilder<FindBudgetBuilder> {

    private final FlowFactory  flowFactory;
    @Autowired
    public FindBudgetBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public FindBudgetBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(GetBudgetParamsHandler.class)
                .addAction(ConvertBudgePageToResponseBudgetHandler.class)
                .addAction(SetTotalSpentHandler.class)
                .build();
        return this;
    }
}
