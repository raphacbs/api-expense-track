package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY_INPUT;

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

