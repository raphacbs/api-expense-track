package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.ConvertBudgetToBudgetDtoHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.GetSingleBudgetParamsHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.budget.SetTotalSpentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindSingleBudgetBuilder extends AFlowBuilder<FindSingleBudgetBuilder> {

    private final FlowFactory flowFactory;

    @Autowired
    public FindSingleBudgetBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public FindSingleBudgetBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(GetSingleBudgetParamsHandler.class)
                .addAction(ConvertBudgetToBudgetDtoHandler.class)
                .addAction(SetTotalSpentHandler.class)
                .build();
        return this;
    }
}
