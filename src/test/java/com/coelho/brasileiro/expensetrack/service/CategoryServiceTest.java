package com.coelho.brasileiro.expensetrack.service;

import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.flow.RegisterEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.UpdateEntityBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.DeleteCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.FindCategoryBuilder;
import com.coelho.brasileiro.expensetrack.flow.category.UpdateCategoryBuilder;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    UpdateCategoryBuilder updateCategoryBuilder;
    @Mock
    UpdateEntityBuilder<CategoryInput, Category, CategoryDto> updateEntityBuilder;
    @Mock
    FindCategoryBuilder findCategoryBuilder;
    @Mock
    DeleteCategoryBuilder deleteCategoryBuilder;
    @Mock
    RegisterEntityBuilder<CategoryInput, Category, CategoryDto> registerEntityBuilder;

    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateCategory() {
        CategoryInput categoryInput = new CategoryInput("name", "description", "#FFFFFF", "E", null);
        categoryService.create(categoryInput);
        verify(registerEntityBuilder, times(1)).create(any());
    }

    @Test
    void shouldFindAllCategories() {
        categoryService.findAll(null);
        verify(findCategoryBuilder, times(1)).create(any());
    }

    @Test
    void shouldUpdateCategory() {
        CategoryInput categoryInput = new CategoryInput("name", "description", "#FFFFFF", "E", null);
        categoryService.update(categoryInput);
        verify(updateEntityBuilder, times(1)).create(any());
    }

    @Test
    void shouldDeleteCategory() {
        CategoryInput categoryInput = new CategoryInput("name", "description", "#FFFFFF", "E", null);
        categoryService.delete(categoryInput);
        verify(deleteCategoryBuilder, times(1)).create(any());
    }
}