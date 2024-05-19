package com.coelho.brasileiro.expensetrack.handler.actions.budget;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Budget;

public class ConvertBudgePageToResponseBudgetHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        context.addResponsePage(Converter.INSTANCE.toResponsePage(context.getPage(), Budget.class));
    }
}
