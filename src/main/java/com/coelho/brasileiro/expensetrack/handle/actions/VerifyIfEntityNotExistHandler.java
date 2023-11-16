package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.MapperVerifyEntity;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class VerifyIfEntityNotExistHandler extends AbstractHandler {

    private final MapperVerifyEntity mapperVerifyIfExist;

    public VerifyIfEntityNotExistHandler(MapperVerifyEntity mapperVerifyIfExist) {
        this.mapperVerifyIfExist = mapperVerifyIfExist;
    }

    @Override
    protected void doHandle(Context context) {
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        mapperVerifyIfExist.apply(entity,context, false);
    }
}
