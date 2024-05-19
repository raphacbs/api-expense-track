package com.coelho.brasileiro.expensetrack.handler.actions.category;

import com.coelho.brasileiro.expensetrack.context.Context;
import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.handler.AbstractHandler;
import com.coelho.brasileiro.expensetrack.mapper.Converter;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.springframework.stereotype.Component;

import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY;
import static com.coelho.brasileiro.expensetrack.util.Constants.Category.CATEGORY_DTO;

@Component
public class ConvertCategoryToCategoryDtoHandler extends AbstractHandler {
    @Override
    protected void doHandle(Context context) {
        Category  category = context.getEntity(CATEGORY, Category.class);
        CategoryDto  categoryDto = Converter.INSTANCE.toDto(category);
        context.setDto(CATEGORY_DTO, categoryDto);
    }
}
