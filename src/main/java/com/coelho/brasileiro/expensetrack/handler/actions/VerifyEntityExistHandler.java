package com.coelho.brasileiro.expensetrack.handler.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.MapperVerifyEntity;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class VerifyEntityExistHandler extends AbstractHandler {

    private final MapperVerifyEntity mapperVerifyIfExist;

    public VerifyEntityExistHandler(MapperVerifyEntity mapperVerifyIfExist) {
        this.mapperVerifyIfExist = mapperVerifyIfExist;
    }

    @Override
    protected void doHandle(Context context) {
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        mapperVerifyIfExist.apply(entity,context, true);
    }
}
