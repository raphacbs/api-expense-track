package com.coelho.brasileiro.expensetrack.flow.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handle.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handle.actions.category.ConvertCategoryInputToCategoryHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.ConvertCategoryToCategoryDtoHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.SaveCategoryHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.VerifyIfCategoryExistHandler;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterCategoryBuilder extends AFlowBuilder<RegisterCategoryBuilder> {

    private final FlowFactory  flowFactory;
    private final InputValidator<CategoryInput> inputValidator;
    @Autowired
    public RegisterCategoryBuilder(FlowFactory flowFactory, InputValidator<CategoryInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public RegisterCategoryBuilder create(Context context) {
        ValidateInput<CategoryInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(validateInput)
                .addAction(ConvertCategoryInputToCategoryHandler.class)
                .addAction(VerifyIfCategoryExistHandler.class)
                .addAction(SaveCategoryHandler.class)
                .addAction(ConvertCategoryToCategoryDtoHandler.class)
                .build();
        return this;
    }
}
