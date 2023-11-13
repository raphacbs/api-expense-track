package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.BudgetDto;
import com.coelho.brasileiro.expensetrack.flow.budget.*;
import com.coelho.brasileiro.expensetrack.input.BudgetInput;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class BudgetService {

    private final RegisterBudgetBuilder registerBudgetBuilder;
    private final SchedulerCreateRecurringBudgetBuilder schedulerCreateRecurringBudgetBuilder;

    private final UpdateBudgetBuilder updateBudgetBuilder;
    private final FindBudgetBuilder findBudgetBuilder;

    private final DeleteBudgetBuilder  deleteBudgetBuilder;

    public BudgetService(RegisterBudgetBuilder registerBudgetBuilder,
                         SchedulerCreateRecurringBudgetBuilder schedulerCreateRecurringBudgetBuilder,
                         UpdateBudgetBuilder updateBudgetBuilder,
                         FindBudgetBuilder findBudgetBuilder,
                         DeleteBudgetBuilder deleteBudgetBuilder) {
        this.registerBudgetBuilder = registerBudgetBuilder;
        this.schedulerCreateRecurringBudgetBuilder = schedulerCreateRecurringBudgetBuilder;
        this.updateBudgetBuilder = updateBudgetBuilder;
        this.findBudgetBuilder = findBudgetBuilder;
        this.deleteBudgetBuilder = deleteBudgetBuilder;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BudgetDto create(BudgetInput budgetInput) {
        DefaultContext context = DefaultContext.builder().build();
        context.setBudgetInput(budgetInput);
        context.setEntityNameCurrent("BUDGET");
        registerBudgetBuilder.create(context).build().run();
        return context.getBudgetDto();
    }

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onApplicationStart() {
        // Este método será executado quando o aplicativo Spring Boot iniciar.
        // Você pode chamar a lógica de criação de registros aqui.
        createBudgetRecordsFromRecurringBudget();
    }

    @Scheduled(cron = "0 0 20 * * ?") // Agendado para rodar uma vez por dia às 20hrs
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createBudgetRecordsFromRecurringBudget() {
        System.out.println("Creating budget records from recurring budgets");
        DefaultContext context = DefaultContext.builder().build();
        context.setEntityNameCurrent("BUDGET");
        schedulerCreateRecurringBudgetBuilder.create(context).build().run();
    }

    public Object getBudgets(Map<String, String> params) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setParams(params);
        context.setEntityNameCurrent("BUDGET");
        findBudgetBuilder.create(context).build().run();
        return context.getResponsePage();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BudgetDto update(BudgetInput budgetInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setBudgetInput(budgetInput);
        context.setEntityNameCurrent("BUDGET");
        updateBudgetBuilder.create(context).build().run();
        return context.getBudgetDto();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(BudgetInput input) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setBudgetInput(input);
        context.setEntityNameCurrent("CATEGORY");
        this.deleteBudgetBuilder.create(context).build().run();
    }
}
