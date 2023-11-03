package com.coelho.brasileiro.expensetrack.handle.actions.category.behavior;


import com.coelho.brasileiro.expensetrack.exception.BusinessException;
import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.TransactionTypeEnum;
import com.coelho.brasileiro.expensetrack.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.coelho.brasileiro.expensetrack.util.BusinessCode.CategoryCodes.CATEGORY_INVALID_TYPE;

@Component
public class CategorySearchByTypeBehavior implements CategorySearchBehavior {

    @Override
    public Page<Category> searchPageUnit(CategoryRepository categoryRepository, Map<String, String> params) {
        try {
            return categoryRepository.findByTypeAndIsDeletedFalse(getPageable(params),
                    TransactionTypeEnum.valueOf(params.get("type").toUpperCase()));
        }catch (IllegalArgumentException e){
            throw new BusinessException(CATEGORY_INVALID_TYPE);
        }
    }
}
