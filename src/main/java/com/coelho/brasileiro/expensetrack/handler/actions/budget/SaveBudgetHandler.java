package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.repository.RecurringBudgetRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;

@Component
public class SaveBudgetHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final RecurringBudgetRepository  recurringBudgetRepository;
    public SaveBudgetHandler(BudgetRepository budgetRepository,
                             RecurringBudgetRepository recurringBudgetRepository) {
        this.budgetRepository = budgetRepository;
        this.recurringBudgetRepository = recurringBudgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Budget budget = context.getEntity(BUDGET, Budget.class);
        Budget budgetSaved = this.budgetRepository.save(budget);
        context.setEntity(BUDGET, budgetSaved);
    }
}
