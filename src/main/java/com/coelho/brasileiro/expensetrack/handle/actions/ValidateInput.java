package com.coelho.brasileiro.expensetrack.handle.actions;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.input.Input;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.util.Constants;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidateInput<T> extends AbstractHandler {
    private final InputValidator<T> inputValidator;

    @Autowired
    public ValidateInput(InputValidator<T> inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    protected void doHandle(Context context) {
        inputValidator.validate(((T) context.getInput(context.getEntityNameCurrent() + "_INPUT", Input.class)));
    }
}
