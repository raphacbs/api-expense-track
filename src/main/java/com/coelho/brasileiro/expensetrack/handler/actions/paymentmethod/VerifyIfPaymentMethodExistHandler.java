package com.coelho.brasileiro.expensetrack.handler.actions.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import com.coelho.brasileiro.expensetrack.repository.PaymentMethodRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.PaymentMethodCodes.PAYMENT_METHOD_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.Constants.PaymentMethod.PAYMENT_METHOD;

@Component
public class VerifyIfPaymentMethodExistHandler extends AbstractHandler {
    private final PaymentMethodRepository paymentMethodRepository;

    public VerifyIfPaymentMethodExistHandler(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    protected void doHandle(Context context) {
        PaymentMethod paymentMethodToFind = context.getEntity(PAYMENT_METHOD, PaymentMethod.class);
        this.paymentMethodRepository.findByName(paymentMethodToFind.getName()).ifPresent(category -> {
                    throw new BusinessException(PAYMENT_METHOD_ALREADY_EXISTS);
                }
        );
    }
}
