package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Budget;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class UpdateBudgetInputToBudgetHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);
        Budget budget = context.getEntity(BUDGET, Budget.class);
        Budget budgetUpdate = Converter.INSTANCE.partialUpdate(budgetInput, budget);
        context.setEntity(CATEGORY,budgetUpdate);
    }
}
