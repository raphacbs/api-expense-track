package com.coelho.brasileiro.expensetrack.handler.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class SaveCategoryHandler  extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public SaveCategoryHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Category  category = context.getEntity(CATEGORY, Category.class);
        Category categorySaved = this.categoryRepository.save(category);
        context.setEntity(CATEGORY, categorySaved);
    }
}
