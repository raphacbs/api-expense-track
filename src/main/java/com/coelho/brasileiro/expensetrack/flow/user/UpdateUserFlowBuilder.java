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
public class UpdateUserFlowBuilder extends AFlowBuilder<UpdateUserFlowBuilder> {
    private final FlowFactory flowFactory;
    private final InputValidator<UserInput> inputValidator;

    @Autowired
    public UpdateUserFlowBuilder(FlowFactory flowFactory, InputValidator<UserInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }
    @Override
    public UpdateUserFlowBuilder create(Context context) {
        ValidateInput<UserInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertUserUpdateRequestToUserHandler.class)
                .addAction(FindUserByEmailHandler.class)
                .addAction(UpdateUserEntityHandler.class)
                .addAction(SaveUserHandler.class)
                .addAction(ConvertUserEntityToUserDtoHandler.class)
                .build();
        return this;
    }
}
