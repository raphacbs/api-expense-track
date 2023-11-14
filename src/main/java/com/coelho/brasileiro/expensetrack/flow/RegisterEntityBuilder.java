package com.coelho.brasileiro.expensetrack.flow;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.Dto;
import com.coelho.brasileiro.expensetrack.handle.actions.*;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.model.IEntity;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterEntityBuilder<I extends Input, E extends IEntity, D extends Dto>
        extends AFlowBuilder<RegisterEntityBuilder<I,E,D>> {

    private final FlowFactory flowFactory;
    private final InputValidator<I> inputValidator;
    private final ConvertInputToEntityHandler<I, E> convertInputToEntityHandler;

    private final VerifyEntityExistHandler verifyEntityExistHandler;

    private final SaveEntityHandler saveEntityHandler;

    private final ConvertEntityToDtoHandler<D,E> convertEntityToDtoHandler;

    @Autowired
    public RegisterEntityBuilder(FlowFactory flowFactory, InputValidator<I> inputValidator,
                                 ConvertInputToEntityHandler<I, E> convertInputToEntityHandler,
                                 VerifyEntityExistHandler verifyEntityExistHandler,
                                 SaveEntityHandler saveEntityHandler,
                                 ConvertEntityToDtoHandler<D, E> convertEntityToDtoHandler) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
        this.convertInputToEntityHandler = convertInputToEntityHandler;
        this.verifyEntityExistHandler = verifyEntityExistHandler;
        this.saveEntityHandler = saveEntityHandler;
        this.convertEntityToDtoHandler = convertEntityToDtoHandler;
    }

    @Override
    public RegisterEntityBuilder<I, E, D> create(Context context) {
        ValidateInput<I> validateInput = new ValidateInput<>(inputValidator);

        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(convertInputToEntityHandler)
                .addAction(verifyEntityExistHandler)
                .addAction(saveEntityHandler)
                .addAction(convertEntityToDtoHandler)
                .build();
        return this;
    }
}