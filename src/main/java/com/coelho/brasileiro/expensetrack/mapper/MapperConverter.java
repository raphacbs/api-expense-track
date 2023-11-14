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
import java.util.function.Function;

public class MapperConverter<I extends Input,E extends IEntity, D extends Dto> {
    private static final Map<Class<? extends Input>, Function<Input, IEntity>> CLASS_FUNCTION_MAP = Map.of(
            PaymentMethodInput.class, input -> Converter.INSTANCE.toEntity((PaymentMethodInput) input),
            CategoryInput.class, input -> Converter.INSTANCE.toEntity((CategoryInput)input)
    );

    private static final Map<Class<? extends IEntity>, Function<IEntity, Dto>> mapperToDto = Map.of(
            PaymentMethod.class, input -> Converter.INSTANCE.toDto((PaymentMethod) input),
            Category.class, input -> Converter.INSTANCE.toDto((Category)input)
    );

    public E apply(I input) {
       Function<Input, IEntity> function = CLASS_FUNCTION_MAP.get(input.getClass());
        if (function != null) {
            return (E) function.apply(input);
        }
        throw new MapperRegistryNotFoundException(MessageFormat.format("Mapper for {0} not found",
                input.getClass().getSimpleName()));
    }

    public D apply(E input) {
        Function<IEntity, Dto> function = mapperToDto.get(input.getClass());
        if (function != null) {
            return (D) function.apply(input);
        }
        throw new MapperRegistryNotFoundException(MessageFormat.format("Mapper for {0} not found",
                input.getClass().getSimpleName()));
    }

}
