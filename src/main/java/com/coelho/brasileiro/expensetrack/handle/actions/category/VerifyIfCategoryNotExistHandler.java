package com.coelho.brasileiro.expensetrack.handle.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handle.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_NOT_FOUND;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class VerifyIfCategoryNotExistHandler extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public VerifyIfCategoryNotExistHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Category categoryToFind = context.getEntity(CATEGORY, Category.class);
        Category categoryFounded = this.categoryRepository.findById(categoryToFind.getId())
                .orElseThrow(() -> new BusinessException(CATEGORY_NOT_FOUND)
                );
        context.setEntity(CATEGORY,categoryFounded);
    }
}
