package com.coelho.brasileiro.expensetrack.handler.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConvertPageToResponsePageHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        context.addResponsePage(Converter.INSTANCE.toResponsePage(context.getPage()));
    }
}
