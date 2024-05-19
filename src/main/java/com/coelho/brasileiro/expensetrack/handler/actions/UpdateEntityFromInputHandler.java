package com.coelho.brasileiro.expensetrack.handler.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateEntityFromInputHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        Input input = context.getInput(context.getEntityNameCurrent()+"_INPUT", Input.class);
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        IEntity entityUpdated = Converter.INSTANCE.partialUpdate(input, entity);
        context.setEntity(context.getEntityNameCurrent(), entityUpdated);
    }
}

