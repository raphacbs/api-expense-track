package com.coelho.brasileiro.expensetrack.handle.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.BudgetCodes.BUDGET_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class VerifyIfBudgetNotExistHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;

    public VerifyIfBudgetNotExistHandler(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Budget budgetToFind = context.getEntity(BUDGET, Budget.class);
        Budget budgetFound = this.budgetRepository.findById(budgetToFind.getId())
                .orElseThrow(() -> new BusinessException(BUDGET_NOT_FOUND)
                );
        context.setEntity(BUDGET,budgetFound);
    }
}
