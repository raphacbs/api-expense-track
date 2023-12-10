package com.coelho.brasileiro.expensetrack.service;


import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.PaymentMethodDto;
import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.flow.DeleteEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.RegisterEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.UpdateEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.paymentmethod.FindPaymentMethodBuilder;
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
    private final UpdateEntityBuilder<PaymentMethodInput, PaymentMethod, PaymentMethodDto> updateEntityBuilder;
    private final DeleteEntityBuilder deleteEntityBuilder;

    private final FindPaymentMethodBuilder findPaymentMethodBuilder;

    public PaymentMethodService(RegisterEntityBuilder<PaymentMethodInput,
            PaymentMethod, PaymentMethodDto> registerEntityBuilder,
                                UpdateEntityBuilder<PaymentMethodInput,
                                        PaymentMethod, PaymentMethodDto> updateEntityBuilder, DeleteEntityBuilder deleteEntityBuilder, FindPaymentMethodBuilder findPaymentMethodBuilder) {
        this.registerEntityBuilder = registerEntityBuilder;
        this.updateEntityBuilder = updateEntityBuilder;
        this.deleteEntityBuilder = deleteEntityBuilder;
        this.findPaymentMethodBuilder = findPaymentMethodBuilder;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentMethodDto create(PaymentMethodInput input) {
        DefaultContext context = DefaultContext.builder().build();
        context.setPaymentMethodInput(input);
        context.setEntityNameCurrent(PAYMENT_METHOD);
        registerEntityBuilder.create(context).build().run();
        return context.getPaymentMethodDto();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(PaymentMethodInput input) {
        DefaultContext context = DefaultContext.builder().build();
        context.setPaymentMethodInput(input);
        context.setEntityNameCurrent(PAYMENT_METHOD);
        deleteEntityBuilder.create(context).build().run();
    }

    public ResponsePage<?> findAll(Map<String, String> params) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setParams(params);
        context.setEntityNameCurrent(PAYMENT_METHOD);
        findPaymentMethodBuilder.create(context).build().run();
        return context.getResponsePage();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentMethodDto update(PaymentMethodInput input) {
        DefaultContext context = DefaultContext.builder().build();
        context.setPaymentMethodInput(input);
        context.setEntityNameCurrent(PAYMENT_METHOD);
        updateEntityBuilder.create(context).build().run();
        return context.getPaymentMethodDto();
    }
}
