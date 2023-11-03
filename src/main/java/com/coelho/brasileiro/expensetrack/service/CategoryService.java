package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.flow.category.DeleteCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.FindCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.RegisterCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.UpdateCategoryBuilder;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CategoryService{
   private final RegisterCategoryBuilder registerCategoryBuilder;
   private final UpdateCategoryBuilder updateCategoryBuilder;
   private final FindCategoryBuilder findCategoryBuilder;
   private final DeleteCategoryBuilder  deleteCategoryBuilder;

    public CategoryService(RegisterCategoryBuilder registerCategoryBuilder,
                           UpdateCategoryBuilder updateCategoryBuilder,
                           FindCategoryBuilder findCategoryBuilder,
                           DeleteCategoryBuilder deleteCategoryBuilder) {
        this.registerCategoryBuilder = registerCategoryBuilder;
        this.updateCategoryBuilder = updateCategoryBuilder;
        this.findCategoryBuilder = findCategoryBuilder;
        this.deleteCategoryBuilder = deleteCategoryBuilder;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CategoryDto create(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent("CATEGORY");
        registerCategoryBuilder.create(context).build().run();
        return context.getCategoryDto();
    }
    public ResponsePage<?> findAll(Map<String, String> params){
        DefaultContext  context = DefaultContext.builder().build();
        context.setParams(params);
        context.setEntityNameCurrent("CATEGORY");
        findCategoryBuilder.create(context).build().run();
        return context.getResponsePage();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CategoryDto update(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent("CATEGORY");
        updateCategoryBuilder.create(context).build().run();
        return context.getCategoryDto();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent("CATEGORY");
        deleteCategoryBuilder.create(context).build().run();
    }


}
