package com.coelho.brasileiro.expensetrack.handle.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.CategorySearchBehavior;
import com.coelho.brasileiro.expensetrack.handle.actions.category.behavior.CategorySearchByNameBehavior;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class GetCategoryByNameAndParamsHandler extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public GetCategoryByNameAndParamsHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        if(context.getParams().get("name") == null){
            return;
        }
        CategorySearchBehavior categorySearchBehavior  = new CategorySearchByNameBehavior();
        Page<Category> categoryPage = categorySearchBehavior.searchPageUnit(categoryRepository, context.getParams());
        context.setPage(categoryPage);
    }
}
