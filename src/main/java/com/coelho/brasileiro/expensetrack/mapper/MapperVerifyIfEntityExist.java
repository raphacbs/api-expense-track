package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.exception.MapperVerifyIfEntityExistNotFoundException;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Function;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.PaymentMethodCodes.PAYMENT_METHOD_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.PaymentMethod.PAYMENT_METHOD;

@Component
public class MapperVerifyIfEntityExist {

    private final PaymentMethodRepository paymentMethodRepository;
    private final CategoryRepository categoryRepository;
    private final Map<Class<? extends IEntity>, Function<Context, Context>> CLASS_FUNCTION_MAP = Map.of(
            PaymentMethod.class, this::verifyIfPaymentMethodExist,
            Category.class, this::verifyIfCategoryExist
    );

    public MapperVerifyIfEntityExist(PaymentMethodRepository paymentMethodRepository, CategoryRepository categoryRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.categoryRepository = categoryRepository;
        }

    public void apply(IEntity entity, Context context) {
        Function<Context, Context> function = CLASS_FUNCTION_MAP.get(entity.getClass());
        if (function == null) {
            throw new MapperVerifyIfEntityExistNotFoundException(MessageFormat.format("Mapping for {0} not found",
                    entity.getClass().getSimpleName()));
        }

        function.apply(context);
    }

    private Context verifyIfPaymentMethodExist(Context context){
        PaymentMethod paymentMethodToFind = context.getEntity(PAYMENT_METHOD, PaymentMethod.class);
        this.paymentMethodRepository.findByName(paymentMethodToFind.getName()).ifPresent(category -> {
                    throw new BusinessException(PAYMENT_METHOD_ALREADY_EXISTS);
                }
        );
        return context;
    }
    private Context verifyIfCategoryExist(Context context){
        Category categoryToFind = context.getEntity(CATEGORY, Category.class);
        this.categoryRepository.findByName(categoryToFind.getName()).ifPresent(category -> {
                    throw new BusinessException(CATEGORY_ALREADY_EXISTS);
                }
        );
        return context;
    }

}
