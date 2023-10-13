package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.flow.category.RegisterCategoryBuilder;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {
   private final RegisterCategoryBuilder registerCategoryBuilder;

    public CategoryService(RegisterCategoryBuilder registerCategoryBuilder) {
        this.registerCategoryBuilder = registerCategoryBuilder;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CategoryDto create(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent("CATEGORY");
        registerCategoryBuilder.create(context).build().run();
        return context.getCategoryDto();
    }
}
