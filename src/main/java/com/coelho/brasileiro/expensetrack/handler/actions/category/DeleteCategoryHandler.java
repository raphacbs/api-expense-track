package com.coelho.brasileiro.expensetrack.handler.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class DeleteCategoryHandler extends AbstractHandler {

    @Override
    protected void doHandle(Context context) {
        Category  category = context.getEntity(CATEGORY, Category.class);
        category.setIsDeleted(Boolean.TRUE);
        context.setEntity(CATEGORY, category);
    }
}
