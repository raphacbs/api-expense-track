package com.coelho.brasileiro.expensetrack.handle.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.RecurringBudget;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;
import static com.coelho.brasileiro.expensetrack.util.Constants.RecurringBudget.RECURRING_BUDGET;

@Component
public class ConvertBudgetInputToBudgetHandler extends AbstractHandler {

    private final UserService userService;

    public ConvertBudgetInputToBudgetHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doHandle(Context context) {
        BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);
        Budget budget = Converter.INSTANCE.fromInput(budgetInput,
                this.userService.getUserLogged(),
                budgetInput.getCategoryId());
//                Category.builder().id(UUID.fromString(budgetInput.getCategoryId())).build());
        if (budgetInput.getFrequency() != null) {
            RecurringBudget recurringBudget = Converter.INSTANCE.fromInputToRecurringBudget(budgetInput,
                    this.userService.getUserLogged(),
                    Category.builder().id(UUID.fromString(budgetInput.getCategoryId())).build());
            context.setEntity(RECURRING_BUDGET, recurringBudget);
        }
        context.setEntity(BUDGET, budget);
    }
}
