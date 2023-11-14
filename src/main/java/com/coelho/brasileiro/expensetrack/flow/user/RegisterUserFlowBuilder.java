package com.coelho.brasileiro.expensetrack.flow.user;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.input.UserInput;
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
    private final InputValidator<UserInput> inputValidator;

    @Autowired
    public RegisterUserFlowBuilder(FlowFactory flowFactory, InputValidator<UserInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }
    @Override
    public RegisterUserFlowBuilder create(Context context) {
        ValidateInput<UserInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertUserInputToUserHandler.class)
                .addAction(VerifyIfUserExistHandler.class)
                .addAction(SaveUserHandler.class)
                .addAction(ConvertUserEntityToUserDtoHandler.class)
                .build();
        return this;
    }
}
