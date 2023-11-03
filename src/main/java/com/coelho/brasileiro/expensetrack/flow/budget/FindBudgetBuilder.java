package com.coelho.brasileiro.expensetrack.flow.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ConvertPageToResponsePageHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.ConvertBudgePageToResponseBudgetHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.GetBudgetParamsHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.GetCategoryAllAndParamsHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.GetCategoryByDescriptionOrNameAndParamsHandler;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
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
                .build();
        return this;
    }
}
