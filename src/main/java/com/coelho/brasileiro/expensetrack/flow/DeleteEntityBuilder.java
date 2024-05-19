package com.coelho.brasileiro.expensetrack.flow;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.actions.ConvertInputToEntityHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.DeleteEntityHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.VerifyIfEntityNotExistHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteEntityBuilder extends AFlowBuilder<DeleteEntityBuilder> {

    private final FlowFactory  flowFactory;
    @Autowired
    public DeleteEntityBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public DeleteEntityBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(ConvertInputToEntityHandler.class)
                .addAction(VerifyIfEntityNotExistHandler.class)
                .addAction(DeleteEntityHandler.class)
                .build();
        return this;
    }
}
