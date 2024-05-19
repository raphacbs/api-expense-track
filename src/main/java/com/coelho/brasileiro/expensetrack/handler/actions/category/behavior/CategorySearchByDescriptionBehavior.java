package com.coelho.brasileiro.expensetrack.handler.actions.category.behavior;


import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategorySearchByDescriptionBehavior implements CategorySearchBehavior {

    @Override
    public Page<Category> searchPageUnit(CategoryRepository categoryRepository, Map<String, String> params) {
        return categoryRepository.findByDescriptionContainingIgnoreCaseAndIsDeletedFalse(getPageable(params),
                params.get("description"));
    }
}
