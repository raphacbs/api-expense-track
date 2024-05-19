package com.coelho.brasileiro.expensetrack.flow.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.category.ConvertCategoryInputToCategoryHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.category.DeleteCategoryHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.category.VerifyIfCategoryNotExistHandler;
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
                .addAction(DeleteCategoryHandler.class)
                .build();
        return this;
    }
}
