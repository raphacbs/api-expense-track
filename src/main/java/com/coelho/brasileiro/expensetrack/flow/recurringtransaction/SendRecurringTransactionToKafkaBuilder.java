package com.coelho.brasileiro.expensetrack.flow.recurringtransaction;


import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import jakarta.inject.Named;
import org.springframework.stereotype.Component;

@Component
@Named("sendRecurringTransactionToKafkaBuilder")
public class SendRecurringTransactionToKafkaBuilder extends AFlowBuilder<SendRecurringTransactionToKafkaBuilder> {

    private final FlowFactory flowFactory;

    public SendRecurringTransactionToKafkaBuilder(FlowFactory flowFactory) {
        this.flowFactory = flowFactory;
    }

    @Override
    public SendRecurringTransactionToKafkaBuilder create(Context context) {
        flow = flowFactory
                .start()
                .context(context)

                .build();

        return this;
    }

}
