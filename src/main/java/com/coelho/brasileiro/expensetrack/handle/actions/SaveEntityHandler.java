package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.MapperSaveEntity;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;


@Component
public class SaveEntityHandler extends AbstractHandler {
    private final MapperSaveEntity mapperSaveEntity;

    public SaveEntityHandler(MapperSaveEntity mapperSaveEntity) {
        this.mapperSaveEntity = mapperSaveEntity;
    }

    @Override
    protected void doHandle(Context context) {
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        mapperSaveEntity.apply(entity,context);
    }
}
