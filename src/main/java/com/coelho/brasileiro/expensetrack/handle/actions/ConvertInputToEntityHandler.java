package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class ConvertInputToEntityHandler extends AbstractHandler {

    @Override
    protected void doHandle(Context context) {
        Input input = context.getInput(context.getEntityNameCurrent() + "_INPUT", Input.class);
        IEntity entity  = Converter.INSTANCE.toEntity(input);
        context.setEntity(context.getEntityNameCurrent(), entity);
    }
}
