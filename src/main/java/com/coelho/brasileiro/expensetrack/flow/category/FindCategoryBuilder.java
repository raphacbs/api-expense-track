package com.coelho.brasileiro.expensetrack.flow.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.flow.AFlowBuilder;
import com.coelho.brasileiro.expensetrack.flow.FlowFactory;
import com.coelho.brasileiro.expensetrack.handler.actions.ConvertPageToResponsePageHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.ValidateInput;
import com.coelho.brasileiro.expensetrack.handler.actions.category.GetCategoryAllAndParamsHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.category.GetCategoryByDescriptionOrNameAndParamsHandler;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.validator.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindCategoryBuilder extends AFlowBuilder<FindCategoryBuilder> {

    private final FlowFactory  flowFactory;
    private final InputValidator<CategoryInput> inputValidator;
    @Autowired
    public FindCategoryBuilder(FlowFactory flowFactory, InputValidator<CategoryInput> inputValidator) {
        this.flowFactory = flowFactory;
        this.inputValidator = inputValidator;
    }

    @Override
    public FindCategoryBuilder create(Context context) {
        ValidateInput<CategoryInput> validateInput = new ValidateInput<>(inputValidator);
        flow = flowFactory
                .start()
                .context(context)
                .addAction(GetCategoryAllAndParamsHandler.class)
                .addAction(GetCategoryByDescriptionOrNameAndParamsHandler.class)
                .addAction(ConvertPageToResponsePageHandler.class)
                .build();
        return this;
    }
}
