package com.coelho.brasileiro.expensetrack.handler.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.handler.actions.category.behavior.CategorySearchBehavior;
import com.coelho.brasileiro.expensetrack.handler.actions.category.behavior.CategorySearchByTypeBehavior;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryByTypeAndParamsHandler extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public GetCategoryByTypeAndParamsHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        if(context.getParams().get("type") == null){
            return;
        }
        CategorySearchBehavior categorySearchBehavior  = new CategorySearchByTypeBehavior();
        Page<Category> categoryPage = categorySearchBehavior.searchPageUnit(categoryRepository, context.getParams());
        context.setPage(categoryPage);
    }
}
