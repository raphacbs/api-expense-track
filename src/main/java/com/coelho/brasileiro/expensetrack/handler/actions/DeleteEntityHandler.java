package com.coelho.brasileiro.expensetrack.handler.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.MapperSaveEntity;
import com.coelho.brasileiro.expensetrack.model.EntityDeletable;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteEntityHandler extends AbstractHandler {

    private final MapperSaveEntity mapperSaveEntity;

    public DeleteEntityHandler(MapperSaveEntity mapperSaveEntity) {
        this.mapperSaveEntity = mapperSaveEntity;
    }

    @Override
    protected void doHandle(Context context) {
        IEntity entity = context.getEntity(context.getEntityNameCurrent(), IEntity.class);
        EntityDeletable entityDeletable = (EntityDeletable) entity;
        entityDeletable.setIsDeleted(Boolean.TRUE);
        mapperSaveEntity.apply(entityDeletable,context);
    }
}
