package com.coelho.brasileiro.expensetrack.handle.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.RecurringBudget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.repository.RecurringBudgetRepository;
import com.coelho.brasileiro.expensetrack.util.FrequencyUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET_INPUT;
import static com.coelho.brasileiro.expensetrack.util.Constants.RecurringBudget.RECURRING_BUDGET;


@Component
public class SaveWithRecurringBudgetHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final RecurringBudgetRepository recurringBudgetRepository;

    public SaveWithRecurringBudgetHandler(BudgetRepository budgetRepository, RecurringBudgetRepository recurringBudgetRepository) {
        this.budgetRepository = budgetRepository;
        this.recurringBudgetRepository = recurringBudgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        BudgetInput budgetInput = context.getInput(BUDGET_INPUT, BudgetInput.class);

        if (budgetInput.getFrequency() == null) {
            saveSingleBudget(context);
        } else {
            saveRecurringBudgets(context);
        }
    }

    private void saveSingleBudget(Context context) {
        Budget budget = context.getEntity(BUDGET, Budget.class);
        Budget budgetSaved = budgetRepository.save(budget);
        context.setEntity(BUDGET, budgetSaved);
    }

    private void saveRecurringBudgets(Context context) {
        RecurringBudget recurringBudget = context.getEntity(RECURRING_BUDGET, RecurringBudget.class);
        LocalDateTime currentDate = recurringBudget.getStartDate();
        LocalDateTime endDate = recurringBudget.getEndDate() == null ? currentDate.plusYears(1) : recurringBudget.getEndDate();
        RecurringBudget recurringBudgetSaved = recurringBudgetRepository.save(recurringBudget);

        List<Budget> budgets = new ArrayList<>();

        while (currentDate.isBefore(endDate)) {
            LocalDateTime endDateTemp = FrequencyUtils.calculateEndDate(currentDate, recurringBudget.getFrequency());
            if(endDateTemp.isAfter(endDate)){
                break;
            }
            createAndSaveBudget(recurringBudget, currentDate, endDateTemp, recurringBudgetSaved, budgets);
            currentDate = FrequencyUtils.calculateNextDate(currentDate, recurringBudget.getFrequency());
        }

        budgetRepository.saveAll(budgets);
        context.setEntity(RECURRING_BUDGET, recurringBudgetSaved);
    }

    private void createAndSaveBudget(RecurringBudget recurringBudget, LocalDateTime startDate, LocalDateTime endDate, RecurringBudget recurringBudgetSaved, List<Budget> budgets) {
        Budget budget = new Budget();
        budget.setAmount(recurringBudget.getAmount());
        budget.setName(recurringBudget.getName());
        budget.setNotes(recurringBudget.getNotes());
        budget.setStartDate(startDate);
        budget.setEndDate(endDate);
        budget.setActive(true);
        budget.setUser(recurringBudget.getUser());
        budget.setCategory(recurringBudget.getCategory());
        budget.setIsDeleted(false);
        budget.setParentId(recurringBudgetSaved.getId());
        budgets.add(budget);
    }



    private boolean verifyStartDate(LocalDateTime startDate) {
        return startDate != null;
    }
}

