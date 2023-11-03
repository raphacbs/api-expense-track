package com.coelho.brasileiro.expensetrack.flow.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.budget.DeleteBudgetHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.*;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCategoryBuilder extends AFlowBuilder<DeleteCategoryBuilder> {

    private final FlowFactory  flowFactory;
    @Autowired
    public DeleteCategoryBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public DeleteCategoryBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(ConvertCategoryInputToCategoryHandler.class)
                .addAction(VerifyIfCategoryNotExistHandler.class)
                .addAction(UpdateCategoryInputToCategoryHandler.class)
                .addAction(DeleteBudgetHandler.class)
                .build();
        return this;
    }
}
