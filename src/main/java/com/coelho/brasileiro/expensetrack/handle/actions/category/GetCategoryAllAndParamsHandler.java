package com.coelho.brasileiro.expensetrack.handle.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.CategorySearchAllBehavior;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.CategorySearchBehavior;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.CategorySearchByTypeBehavior;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryAllAndParamsHandler extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public GetCategoryAllAndParamsHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        if(context.getParams().get("type") == null &&
                context.getParams().get("name") == null &&
                context.getParams().get("description") == null){
            CategorySearchBehavior categorySearchBehavior  = new CategorySearchAllBehavior();
            Page<Category> categoryPage = categorySearchBehavior.searchPageUnit(categoryRepository, context.getParams());
            context.setPage(categoryPage);
        }

    }
}
