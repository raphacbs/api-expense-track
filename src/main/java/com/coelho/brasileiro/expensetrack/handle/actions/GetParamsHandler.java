package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.lang.reflect.ParameterizedType;
import java.util.Map;
import java.util.UUID;

@Component
public class GetParamsHandler<T extends IEntity> extends AbstractHandler {

    private final Map<Class<?>, JpaRepository<?, ?>> modelRepositoryMapping;

    public GetParamsHandler( Map<Class<?>, JpaRepository<?, ?>> modelRepositoryMapping) {
        this.modelRepositoryMapping = modelRepositoryMapping;
    }

    @Override
    protected void doHandle(Context context) {
        factoryRepository().findAll();
    }

    private JpaRepository<T, UUID> factoryRepository(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass()
                .getGenericSuperclass();
        Class<T> modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
        return (JpaRepository<T, UUID>) this.modelRepositoryMapping.get(modelClass);
    }
}
