package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;

@Component
public class GetSingleBudgetParamsHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final UserService userService;

    public GetSingleBudgetParamsHandler(BudgetRepository budgetRepository, UserService userService) {
        this.budgetRepository = budgetRepository;
        this.userService = userService;
    }


    @Override
    protected void doHandle(Context context) {
        if (context.getInput(BUDGET_INPUT, BudgetInput.class) != null) {
            BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);
            Budget budget = this.budgetRepository.findByUserAndId(
                    userService.getUserLogged(),
                    UUID.fromString(budgetInput.getId()));
            context.setEntity(BUDGET, budget);

        }
    }

}
