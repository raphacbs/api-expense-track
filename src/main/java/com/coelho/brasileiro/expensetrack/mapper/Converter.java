package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.dto.CategoryDto;
import com.coelho.brasileiro.expensetrack.dto.UserDto;
import com.coelho.brasileiro.expensetrack.input.CategoryInput;
import com.coelho.brasileiro.expensetrack.input.UserInput;
import com.coelho.brasileiro.expensetrack.input.UserUpdate;
import com.coelho.brasileiro.expensetrack.input.LoginInput;
import com.coelho.brasileiro.expensetrack.model.Category;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {DateTimeFormatter.class})

public interface Converter {
    Converter INSTANCE = Mappers.getMapper(Converter.class);
    com.coelho.brasileiro.expensetrack.model.User toEntity(UserDto userDTO);

    com.coelho.brasileiro.expensetrack.model.User toEntity(LoginInput loginInputRequest);

    @Mapping(target = "createdAt", expression = "java(user.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))")
    UserDto toDto(com.coelho.brasileiro.expensetrack.model.User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserDto userDTO, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="password", ignore = true)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserInput userInput, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target="password", ignore = true)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserUpdate userUpdateRequest, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    com.coelho.brasileiro.expensetrack.model.User toEntity(UserInput userInput);
    com.coelho.brasileiro.expensetrack.model.User toEntity(UserUpdate userUpdateRequest);

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);

    List<Category> toEntityList(List<CategoryDto> categories);

    @Mapping(target = "id", ignore = true)
    Category fromInput(CategoryInput categoryInput);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);
}