package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.category.behavior.Params;
import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.repository.BudgetRepository;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.coelho.brasileiro.expensetrack.constants.Params.END_DATE;
import static com.coelho.brasileiro.expensetrack.constants.Params.START_DATE;

@Component
public class GetBudgetParamsHandler extends AbstractHandler {
    private final BudgetRepository budgetRepository;
    private final UserService userService;

    public GetBudgetParamsHandler(BudgetRepository budgetRepository, UserService userService) {
        this.budgetRepository = budgetRepository;
        this.userService = userService;
    }


    @Override
    protected void doHandle(Context context) {

        LocalDateTime startDate = LocalDate.parse(context.getParams().get(START_DATE)).atStartOfDay();

        LocalDateTime endDate = context.getParams().get(END_DATE) != null
                ? LocalDate.parse(context.getParams().get(END_DATE)).atTime(23, 59, 59)
                : null;

        Page<Budget> budgetPage = endDate == null
                ? this.budgetRepository.findByStartDateGreaterThanEqual(
                startDate,
                userService.getUserLogged(),
                Params.getPageable(context.getParams()))
                : this.budgetRepository.findByStartDateBetweenAndUser(
                startDate,
                endDate,
                userService.getUserLogged(),
                Params.getPageable(context.getParams())
        );
        context.setPage(budgetPage);
    }

}
