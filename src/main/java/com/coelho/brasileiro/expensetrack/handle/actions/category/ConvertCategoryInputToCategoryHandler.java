package com.coelho.brasileiro.expensetrack.handle.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY_INPUT;

@Component
public class ConvertCategoryInputToCategoryHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        CategoryInput categoryInput = context.getInput(CATEGORY_INPUT, CategoryInput.class);
        Category  category = Converter.INSTANCE.fromInput(categoryInput);
        context.setEntity(CATEGORY,category);
    }
}
