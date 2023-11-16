package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.exception.MapperRegistryNotFoundException;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.input.PaymentMethodInput;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapperConverter<I extends Input,E extends IEntity, D extends Dto> {
    private static final Map<Class<? extends Input>, Function<Input, IEntity>> mapperFromInputToEntity = Map.of(
            PaymentMethodInput.class, input -> Converter.INSTANCE.toEntity((PaymentMethodInput) input),
            CategoryInput.class, input -> Converter.INSTANCE.toEntity((CategoryInput)input)
    );

    private static final Map<Class<? extends IEntity>, Function<IEntity, Dto>> mapperFromEntityToDto = Map.of(
            PaymentMethod.class, input -> Converter.INSTANCE.toDto((PaymentMethod) input),
            Category.class, input -> Converter.INSTANCE.toDto((Category)input)
    );

    private static final Map<Class<? extends IEntity>, BiFunction<Input, IEntity, IEntity>> mapperUpdateEntityFromInput = Map.of(
            PaymentMethod.class, (input, entity) -> Converter.INSTANCE.partialUpdate((PaymentMethodInput)  input, (PaymentMethod) entity),
            Category.class,  (input, entity) -> Converter.INSTANCE.partialUpdate((CategoryInput)  input, (Category) entity)
    );

    public E apply(I input) {
       Function<Input, IEntity> function = mapperFromInputToEntity.get(input.getClass());
        if (function != null) {
            return (E) function.apply(input);
        }
        throw new MapperRegistryNotFoundException(MessageFormat.format("Mapper for {0} not found",
                input.getClass().getSimpleName()));
    }

    public D apply(E entity) {
        Function<IEntity, Dto> function = mapperFromEntityToDto.get(entity.getClass());
        if (function != null) {
            return (D) function.apply(entity);
        }
        throw new MapperRegistryNotFoundException(MessageFormat.format("Mapper for {0} not found",
                entity.getClass().getSimpleName()));
    }

    public D apply(I input, E entity ) {
        BiFunction<Input, IEntity, IEntity>  function = mapperUpdateEntityFromInput.get(entity.getClass());
        if (function != null) {
            return (D) function.apply(input, entity);
        }
        throw new MapperRegistryNotFoundException(MessageFormat.format("Mapper for {0} not found",
                entity.getClass().getSimpleName()));
    }

}
