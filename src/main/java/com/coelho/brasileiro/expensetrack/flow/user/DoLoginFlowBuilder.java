package com.coelho.brasileiro.expensetrack.flow.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.actions.user.ConvertInputToEntityHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.user.FindUserByEmailAndPasswordHandler;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.user.ConvertEntityToDtoHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.user.GenerateTokenHandler;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoLoginFlowBuilder extends AFlowBuilder<DoLoginFlowBuilder> {
    private final FlowFactory flowFactory;
    private final InputValidator<LoginRequest> inputValidator;

    @Autowired
    public DoLoginFlowBuilder(FlowFactory flowFactory, InputValidator<LoginRequest> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }
    @Override
    public DoLoginFlowBuilder create(Context context) {
        ValidateInput<LoginRequest> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertInputToEntityHandler.class)
                .addAction(FindUserByEmailAndPasswordHandler.class)
                .addAction(ConvertEntityToDtoHandler.class)
                .addAction(GenerateTokenHandler.class)
                .build();
        return this;
    }
}
