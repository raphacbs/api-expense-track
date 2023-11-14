package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class ConvertEntityToDtoHandler<D extends Dto, E extends IEntity> extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        E entity = (E) context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        D dto = Converter.INSTANCE.toDto(entity);
        context.setDto(context.getEntityNameCurrent()+"_DTO", dto);
    }
}
