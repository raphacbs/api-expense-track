package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.MapperVerifyIfEntityExist;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class VerifyEntityExistHandler extends AbstractHandler {

    private final MapperVerifyIfEntityExist mapperVerifyIfExist;

    public VerifyEntityExistHandler(MapperVerifyIfEntityExist mapperVerifyIfExist) {
        this.mapperVerifyIfExist = mapperVerifyIfExist;
    }

    @Override
    protected void doHandle(Context context) {
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        mapperVerifyIfExist.apply(entity,context);
    }
}
