package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.context.DefaultContext;
import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.dto.ResponsePage;
import com.coelho.brasileiro.expensetrack.flow.RegisterEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.DeleteCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.FindCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.RegisterCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.UpdateCategoryBuilder;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;

@Service
public class CategoryService{
   private final UpdateCategoryBuilder updateCategoryBuilder;
   private final FindCategoryBuilder findCategoryBuilder;
   private final DeleteCategoryBuilder  deleteCategoryBuilder;

   private final RegisterEntityBuilder<CategoryInput, Category, CategoryDto> registerEntityBuilder;

    public CategoryService(UpdateCategoryBuilder updateCategoryBuilder,
                           FindCategoryBuilder findCategoryBuilder,
                           DeleteCategoryBuilder deleteCategoryBuilder,
                           RegisterEntityBuilder<CategoryInput, Category, CategoryDto> registerEntityBuilder) {
        this.updateCategoryBuilder = updateCategoryBuilder;
        this.findCategoryBuilder = findCategoryBuilder;
        this.deleteCategoryBuilder = deleteCategoryBuilder;
        this.registerEntityBuilder = registerEntityBuilder;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CategoryDto create(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent(CATEGORY);
        registerEntityBuilder.create(context).build().run();
        return context.getCategoryDto();
    }
    public ResponsePage<?> findAll(Map<String, String> params){
        DefaultContext  context = DefaultContext.builder().build();
        context.setParams(params);
        context.setEntityNameCurrent(CATEGORY);
        findCategoryBuilder.create(context).build().run();
        return context.getResponsePage();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public CategoryDto update(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent(CATEGORY);
        updateCategoryBuilder.create(context).build().run();
        return context.getCategoryDto();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(CategoryInput  categoryInput) {
        DefaultContext  context = DefaultContext.builder().build();
        context.setCategoryInput(categoryInput);
        context.setEntityNameCurrent(CATEGORY);
        deleteCategoryBuilder.create(context).build().run();
    }


}
