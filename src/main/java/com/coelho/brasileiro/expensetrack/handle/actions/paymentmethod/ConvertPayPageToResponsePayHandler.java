package com.coelho.brasileiro.expensetrack.handle.actions.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.PaymentMethod;


public class ConvertPayPageToResponsePayHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        context.addResponsePage(Converter.INSTANCE.toResponsePage(context.getPage(), PaymentMethod.class));
    }
}
