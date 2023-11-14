package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.PaymentMethodDto;
import com.coelho.brasileiro.expensetrack.flow.RegisterEntityBuilder;
import com.coelho.brasileiro.expensetrack.input.PaymentMethodInput;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.coelho.brasileiro.expensetrack.util.Constants.PaymentMethod.PAYMENT_METHOD;

@Service
public class PaymentMethodService {
private final RegisterEntityBuilder<PaymentMethodInput, PaymentMethod, PaymentMethodDto> registerEntityBuilder;

    public PaymentMethodService(RegisterEntityBuilder<PaymentMethodInput, PaymentMethod, PaymentMethodDto> registerEntityBuilder) {
        this.registerEntityBuilder = registerEntityBuilder;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentMethodDto create(PaymentMethodInput input){
        DefaultContext  context = DefaultContext.builder().build();
        context.setPaymentMethodInput(input);
        context.setEntityNameCurrent(PAYMENT_METHOD);
        registerEntityBuilder.create(context).build().run();
        return context.getPaymentMethodDto();
    }

    public void delete(PaymentMethodInput input) {
    }

    public Object findAll(Map<String, String> params) {
        return null;
    }

    public Object update(PaymentMethodInput input) {
        return null;
    }
}
