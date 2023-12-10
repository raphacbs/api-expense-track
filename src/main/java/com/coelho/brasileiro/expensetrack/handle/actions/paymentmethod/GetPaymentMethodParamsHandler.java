package com.coelho.brasileiro.expensetrack.handle.actions.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.Params;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import com.coelho.brasileiro.expensetrack.service.UserService;
import com.coelho.brasileiro.expensetrack.util.BusinessCode;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.coelho.brasileiro.expensetrack.util.Checks.check;
import static com.coelho.brasileiro.expensetrack.util.Checks.isNull;

@Component
public class GetPaymentMethodParamsHandler extends AbstractHandler {
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserService userService;

    public GetPaymentMethodParamsHandler(PaymentMethodRepository paymentMethodRepository,
                                         UserService userService) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.userService = userService;
    }

    @Override
    protected void doHandle(Context context) {

        String name = context.getParams().get("name");
        String description = context.getParams().get("description");

        Page<PaymentMethod> page;

        if (isNull(name) && isNull(description)) {
            page = findByUserId(context);
        } else {
            page = findByNameOrDescription(name, description, context);
        }

        context.setPage(page);

    }

    private Page<PaymentMethod> findByUserId(Context context) {
        return this.paymentMethodRepository.findByUserId(
                userService.getUserLogged().getId(),
                Params.getPageable(context.getParams())
        );
    }

    private Page<PaymentMethod> findByNameOrDescription(String name, String description, Context context) {
        return this.paymentMethodRepository.findByNameOrDescription(
                name,
                description,
                userService.getUserLogged().getId(),
                Params.getPageable(context.getParams())
        );
    }


}
