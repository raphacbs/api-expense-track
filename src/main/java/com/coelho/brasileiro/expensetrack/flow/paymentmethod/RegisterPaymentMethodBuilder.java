package com.coelho.brasileiro.expensetrack.flow.paymentmethod;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.*;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterPaymentMethodBuilder<I extends Input, E extends IEntity, D extends Dto>
        extends AFlowBuilder<RegisterPaymentMethodBuilder<I,E,D>> {

    private final FlowFactory flowFactory;
    private final InputValidator<I> inputValidator;

    @Autowired
    public RegisterPaymentMethodBuilder(FlowFactory flowFactory, InputValidator<I> inputValidator)                       {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;

    }

    @Override
    public RegisterPaymentMethodBuilder<I, E, D> create(Context context) {
        ValidateInput<I> validateInput = new ValidateInput<>(inputValidator);

        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertInputToEntityHandler.class)
                .addAction(VerifyEntityExistHandler.class)
                .addAction(SaveEntityHandler.class)
                .addAction(ConvertEntityToDtoHandler.class)
                .build();
        return this;
    }
}