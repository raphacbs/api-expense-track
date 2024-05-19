package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.repository.RecurringBudgetRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;

@Component
public class DeleteBudgetHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final RecurringBudgetRepository recurringBudgetRepository;

    public DeleteBudgetHandler(BudgetRepository budgetRepository,
                               RecurringBudgetRepository recurringBudgetRepository) {
        this.budgetRepository = budgetRepository;
        this.recurringBudgetRepository = recurringBudgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Budget budget = context.getEntity(BUDGET, Budget.class);
        BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);
        if ((boolean) budgetInput.getHeader("recurringDelete")){
            this.budgetRepository.deleteAllByParentId(budget.getParentId());
        }else {
            this.budgetRepository.delete(budget);
        }
    }
}
