package com.coelho.brasileiro.expensetrack.handler.actions.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;


public class ConvertPayPageToResponsePayHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        context.addResponsePage(Converter.INSTANCE.toResponsePage(context.getPage(), PaymentMethod.class));
    }
}
