package com.coelho.brasileiro.expensetrack.handler.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_ALREADY_EXISTS;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Component
public class VerifyIfCategoryExistHandler extends AbstractHandler {
    private final CategoryRepository categoryRepository;

    public VerifyIfCategoryExistHandler(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    protected void doHandle(Context context) {
        Category categoryToFind = context.getEntity(CATEGORY, Category.class);
        this.categoryRepository.findByName(categoryToFind.getName()).ifPresent(category -> {
                    throw new BusinessException(CATEGORY_ALREADY_EXISTS);
                }
        );
    }
}
