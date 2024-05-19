package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.RecurringBudget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.repository.RecurringBudgetRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.BudgetCodes.BUDGET_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.Budget.BUDGET;

@Component
public class CreateBudgetRecordsFromRecurringBudgetHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final RecurringBudgetRepository recurringBudgetRepository;

    public CreateBudgetRecordsFromRecurringBudgetHandler(BudgetRepository budgetRepository, RecurringBudgetRepository recurringBudgetRepository) {
        this.budgetRepository = budgetRepository;
        this.recurringBudgetRepository = recurringBudgetRepository;
    }

    @Override
    protected void doHandle(Context context) {
        List<RecurringBudget> recurringBudgets = recurringBudgetRepository.findActiveRecurringBudgets();
        recurringBudgets.forEach(recurringBudget -> {
            UUID recurringBudgetId = recurringBudget.getId();
            List<Budget> budgets = this.budgetRepository.findLastBudgetByParentId(recurringBudgetId);
            LocalDateTime nextStartDate = null;
            LocalDateTime nextEndDate = null;
            if (budgets.isEmpty()) {
                nextStartDate = calculateNextDate(recurringBudget.getStartDate(),
                        recurringBudget.getFrequency().name());
                nextEndDate = calculateEndDate(nextStartDate, recurringBudget.getFrequency().name());
            } else {
                Budget lastBudget = budgets.stream().findFirst().orElseThrow(() -> new BusinessException(BUDGET_NOT_FOUND));
                nextStartDate = calculateNextDate(lastBudget.getStartDate(), recurringBudget.getFrequency().name());
                nextEndDate = calculateEndDate(nextStartDate, recurringBudget.getFrequency().name());
            }
            createBudget(recurringBudget, nextStartDate, nextEndDate);
        });
    }

    private void saveSingleBudget(Context context) {
        Budget budget = context.getEntity(BUDGET, Budget.class);
        Budget budgetSaved = budgetRepository.save(budget);
        context.setEntity(BUDGET, budgetSaved);
    }


    private void createBudget(RecurringBudget recurringBudget, LocalDateTime startDate, LocalDateTime endDate) {
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
        budget.setParentId(recurringBudget.getId());
        this.budgetRepository.save(budget);
    }

    private LocalDateTime calculateNextDate(LocalDateTime currentDate, String frequency) {
        if ("MONTHLY".equals(frequency)) {
            return currentDate.plusMonths(1);
        } else if ("ANNUAL".equals(frequency)) {
            return currentDate.plusYears(1);
        } else if ("BIWEEKLY".equals(frequency)) {
            return currentDate.plusWeeks(2);
        } else if ("WEEKLY".equals(frequency)) {
            return currentDate.plusWeeks(1);
        } else if ("DAILY".equals(frequency)) {
            return currentDate.plusDays(1);
        } else {
            throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
    }

    private LocalDateTime calculateEndDate(LocalDateTime startDate, String frequency) {
        switch (frequency) {
            case "MONTHLY":
                return startDate.minusDays(1).plusMonths(1).withHour(23).withMinute(59).withSecond(59);
            case "ANNUAL":
                return startDate.minusDays(1).plusYears(1).withHour(23).withMinute(59).withSecond(59);
            case "BIWEEKLY":
                return startDate.minusDays(1).plusWeeks(2).withHour(23).withMinute(59).withSecond(59);
            case "WEEKLY":
                return startDate.minusDays(1).plusWeeks(1).withHour(23).withMinute(59).withSecond(59);
            case "DAILY":
                return startDate.withHour(23).withMinute(59).withSecond(59);
            default:
                throw new IllegalArgumentException("Invalid frequency: " + frequency);
        }
    }

}

