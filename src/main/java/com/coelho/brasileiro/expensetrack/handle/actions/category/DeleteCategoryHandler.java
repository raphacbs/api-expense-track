package com.coelho.brasileiro.expensetrack.handle.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class DeleteCategoryHandler extends AbstractHandler {

    @Override
    protected void doHandle(Context context) {
        Category  category = context.getEntity(CATEGORY, Category.class);
        category.setDeleted(true);
        context.setEntity(CATEGORY, category);
    }
}
