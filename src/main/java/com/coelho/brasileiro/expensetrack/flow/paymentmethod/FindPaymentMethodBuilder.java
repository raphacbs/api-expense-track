package com.coelho.brasileiro.expensetrack.flow.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.paymentmethod.ConvertPayPageToResponsePayHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.paymentmethod.GetPaymentMethodParamsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindPaymentMethodBuilder extends AFlowBuilder<FindPaymentMethodBuilder> {

    private final FlowFactory  flowFactory;
    @Autowired
    public FindPaymentMethodBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public FindPaymentMethodBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)
                .addAction(GetPaymentMethodParamsHandler.class)
                .addAction(ConvertPayPageToResponsePayHandler.class)
                .build();
        return this;
    }
}
