package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.MapperVerifyIfEntityExistNotFoundException;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.model.MoneyBox;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import com.coelho.brasileiro.expensetrack.repository.MoneyBoxRepository;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import com.coelho.brasileiro.expensetrack.service.UserService;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Map;
import java.util.function.Function;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.MoneyBox.MONEY_BOX;
import static com.coelho.brasileiro.expensetrack.util.Constants.PaymentMethod.PAYMENT_METHOD;

@Component
public class MapperSaveEntity {
    private final PaymentMethodRepository paymentMethodRepository;
    private final CategoryRepository categoryRepository;
    private final MoneyBoxRepository moneyBoxRepository;
    private final UserService userService;
    private final Map<Class<? extends IEntity>, Function<Context, Context>> CLASS_FUNCTION_MAP = Map.of(
            PaymentMethod.class, this::savePaymentMethod,
            Category.class, this::saveCategory,
            MoneyBox.class, this::saveMoneyBox
    );

    public MapperSaveEntity(PaymentMethodRepository paymentMethodRepository,
                            CategoryRepository categoryRepository,
                            MoneyBoxRepository moneyBoxRepository, UserService userService) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.categoryRepository = categoryRepository;
        this.moneyBoxRepository = moneyBoxRepository;
        this.userService = userService;
    }

    public void apply(IEntity entity, Context context) {
        Function<Context, Context> function = CLASS_FUNCTION_MAP.get(entity.getClass());
        if (function == null) {
            throw new MapperVerifyIfEntityExistNotFoundException(MessageFormat.format("Mapping for {0} not found",
                    entity.getClass().getSimpleName()));
        }

        function.apply(context);
    }

    private Context savePaymentMethod(Context context) {
        PaymentMethod paymentMethod = context.getEntity(PAYMENT_METHOD, PaymentMethod.class);
        paymentMethod.setUser(this.userService.getUserLogged());
        PaymentMethod paymentMethodSaved = this.paymentMethodRepository.save(paymentMethod);
        context.setEntity(PAYMENT_METHOD, paymentMethodSaved);
        return context;
    }

    private Context saveCategory(Context context) {
        Category category = context.getEntity(CATEGORY, Category.class);
        Category categorySaved = this.categoryRepository.save(category);
        context.setEntity(CATEGORY, categorySaved);
        return context;
    }

    private Context saveMoneyBox(Context context) {
        MoneyBox moneyBox = context.getEntity(MONEY_BOX, MoneyBox.class);
        MoneyBox moneyBoxSaved = this.moneyBoxRepository.save(moneyBox);
        context.setEntity(MONEY_BOX, moneyBoxSaved);
        return context;
    }

}
