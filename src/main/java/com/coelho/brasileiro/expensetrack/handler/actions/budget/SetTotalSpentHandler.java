package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.BudgetDto;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.repository.TransactionCustomRepository;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class SetTotalSpentHandler extends AbstractHandler {

    private final UserService userService;
    private final TransactionCustomRepository transactionCustomRepository;

    public SetTotalSpentHandler(UserService userService, TransactionCustomRepository transactionCustomRepository) {
        this.userService = userService;
        this.transactionCustomRepository = transactionCustomRepository;
    }


    @Override
    protected void doHandle(Context context) {

        context.getResponsePage().getItems().forEach(budget -> {
            BudgetDto budgetDto = (BudgetDto) budget;

            budgetDto.setTotalSpent(transactionCustomRepository.findTotalBalance(budgetDto.getId(),
                    budgetDto.getStartDate(),
                    budgetDto.getEndDate(),
                    userService.getUserLogged().getId()));
            budgetDto.setBalance(budgetDto.getAmount().subtract(budgetDto.getTotalSpent()));
        });
    }
}
