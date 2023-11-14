package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class ConvertInputToEntityHandler<I extends Input, E extends IEntity> extends AbstractHandler {

    @Override
    protected void doHandle(Context context) {
        I input = (I) context.getInput(context.getEntityNameCurrent() + "_INPUT", Input.class);
        E entity = Converter.INSTANCE.toEntity(input);
        context.setEntity(context.getEntityNameCurrent(), entity);
    }
}
