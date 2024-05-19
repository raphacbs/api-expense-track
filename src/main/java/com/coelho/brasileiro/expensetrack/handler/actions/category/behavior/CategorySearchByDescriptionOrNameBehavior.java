package com.coelho.brasileiro.expensetrack.handler.actions.category.behavior;


import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CategorySearchByDescriptionOrNameBehavior implements CategorySearchBehavior {

    @Override
    public Page<Category> searchPageUnit(CategoryRepository categoryRepository, Map<String, String> params) {
        return categoryRepository.findByDescriptionContainingIgnoreCaseOrNameContainingIgnoreCaseAndIsDeleted(getPageable(params),
                params.get("description"),
                params.get("name"),
                false);
    }
}
