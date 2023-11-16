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
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.PaymentMethodCodes.PAYMENT_METHOD_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.PaymentMethodCodes.PAYMENT_METHOD_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.PaymentMethod.PAYMENT_METHOD;

@Component
public class MapperVerifyEntity {

    private final PaymentMethodRepository paymentMethodRepository;
    private final CategoryRepository categoryRepository;
    private final Map<Class<? extends IEntity>, Function<Context, Context>> mapperVerifyExist = Map.of(
            PaymentMethod.class, this::verifyIfPaymentMethodExist,
            Category.class, this::verifyIfCategoryExist
    );

    private final Map<Class<? extends IEntity>, Function<Context, Context>> mapperVerifyNotExist = Map.of(
            PaymentMethod.class, this::verifyIfPaymentMethodNotExist,
            Category.class, this::verifyIfCategoryNotExist
    );

    public MapperVerifyEntity(PaymentMethodRepository paymentMethodRepository, CategoryRepository categoryRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.categoryRepository = categoryRepository;
        }

    public void apply(IEntity entity, Context context, boolean isExist) {
        if(isExist){
            apply(entity,context,mapperVerifyExist);
        }else{
            apply(entity,context,mapperVerifyNotExist);
        }
    }

    private void apply(IEntity entity, Context context, Map<Class<? extends IEntity>, Function<Context, Context>> mapper) {
        Function<Context, Context> function = mapper.get(entity.getClass());
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

    private Context verifyIfCategoryNotExist(Context context){
        Category categoryToFind = context.getEntity(CATEGORY, Category.class);
        Category categoryFounded = this.categoryRepository.findById(categoryToFind.getId())
                .orElseThrow(() -> new BusinessException(CATEGORY_NOT_FOUND)
                );
        context.setEntity(CATEGORY,categoryFounded);
        return context;
    }

    private Context verifyIfPaymentMethodNotExist(Context context){
        PaymentMethod paymentMethod = context.getEntity(PAYMENT_METHOD, PaymentMethod.class);
        PaymentMethod paymentMethodFound = this.paymentMethodRepository.findById(paymentMethod.getId())
                .orElseThrow(() -> new BusinessException(PAYMENT_METHOD_NOT_FOUND)
                );
        context.setEntity(PAYMENT_METHOD,paymentMethodFound);
        return context;
    }

}
