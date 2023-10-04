package com.coelho.brasileiro.expensetrack.flow.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.request.UserRequest;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.user.*;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserFlowBuilder extends AFlowBuilder<RegisterUserFlowBuilder> {
    private final FlowFactory flowFactory;
    private final InputValidator<UserRequest> inputValidator;

    @Autowired
    public RegisterUserFlowBuilder(FlowFactory flowFactory, InputValidator<UserRequest> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }
    @Override
    public RegisterUserFlowBuilder create(Context context) {
        ValidateInput<UserRequest> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertUserRequestToUserHandler.class)
                .addAction(VerifyIfUserExistHandler.class)
                .addAction(SaveUserHandler.class)
                .addAction(ConvertEntityToDtoHandler.class)
                .build();
        return this;
    }
}
