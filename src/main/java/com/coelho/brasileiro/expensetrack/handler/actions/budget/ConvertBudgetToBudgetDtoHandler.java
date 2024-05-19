package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.BudgetDto;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.RecurringBudget;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.*;
import static com.coelho.brasileiro.expensetrack.util.Constants.RecurringBudget.RECURRING_BUDGET;

public class ConvertBudgetToBudgetDtoHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);

        if (budgetInput.getFrequency() == null) {
            Budget budget = context.getEntity(BUDGET,Budget.class);
            BudgetDto budgetDto = Converter.INSTANCE.toBudgetDto(budget);
            context.setDto(BUDGET_DTO,budgetDto);
        }else{
            RecurringBudget recurringBudget = context.getEntity(RECURRING_BUDGET, RecurringBudget.class);
            BudgetDto budgetDto = Converter.INSTANCE.toBudgetDto(recurringBudget);
            context.setDto(BUDGET_DTO,budgetDto);
        }

    }
}
