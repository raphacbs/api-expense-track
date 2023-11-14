package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.dto.*;
import com.coelho.brasileiro.expensetrack.input.*;
import com.coelho.brasileiro.expensetrack.model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {Page.class, DateTimeFormatter.class})

public interface Converter {
    Converter INSTANCE = Mappers.getMapper(Converter.class);

    @NullLocaDateToLocalDateTime
    default LocalDateTime nullLocaDateToLocalDateTime(LocalDate date) {
        return date != null ? date.atStartOfDay() : null;

    }
    @LocalDateTimeToString
    default String localDateTimeToString(LocalDateTime date) {
        return date != null ? date.format(DateTimeFormatter.ISO_DATE_TIME): null;
    }

    @NullToCategory
    default Category nullCategory(String categoryId) {
        return categoryId != null ? Category.builder().id(UUID.fromString(categoryId)).build() : null;
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface NullLocaDateToLocalDateTime {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface LocalDateTimeToString {

    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface NullToCategory {

    }


    @NullToCategory
    default IEntity toEntityFromInput(Input input) {
        if (input instanceof PaymentMethodInput) {
            return toEntity((PaymentMethodInput) input);
        }
        return null;
    }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.CLASS)
    @interface ToEntity {

    }


    com.coelho.brasileiro.expensetrack.model.User toEntity(UserDto userDTO);

    com.coelho.brasileiro.expensetrack.model.User toEntity(LoginInput loginInputRequest);

    @Named("toUserDto")
    @Mapping(target = "createdAt", qualifiedBy = LocalDateTimeToString.class)
    @Mapping(target = "initials", expression = "java(user.getFirstName().substring(0,1) + user.getLastName().substring(0,1))")
    UserDto toUserDto(com.coelho.brasileiro.expensetrack.model.User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserDto userDTO, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserInput userInput, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    com.coelho.brasileiro.expensetrack.model.User partialUpdate(UserUpdate userUpdateRequest, @MappingTarget com.coelho.brasileiro.expensetrack.model.User user);

    com.coelho.brasileiro.expensetrack.model.User toEntity(UserInput userInput);

    com.coelho.brasileiro.expensetrack.model.User toEntity(UserUpdate userUpdateRequest);

    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "userId", source = "user.id")
    BudgetDto toBudgetDto(Budget budget);

    List<BudgetDto> toBudgetListDto(List<Budget> budget);

    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "userId", source = "user.id")
    BudgetDto toBudgetDto(RecurringBudget recurringBudget);

    List<CategoryDto> toDtoList(List<Category> categories);

    List<Category> toEntityList(List<CategoryDto> categories);

    Category toEntity(CategoryInput categoryInput);

    @Mapping(target = "isDeleted", expression = "java(false)")
    PaymentMethod toEntity(PaymentMethodInput input);

    @Mapping(target = "user", qualifiedByName = "toUserDto")
    PaymentMethodDto toDto(PaymentMethod paymentMethod);


    default <T extends Input, U extends IEntity> U toEntity(T input) {
        MapperConverter<T, U, Dto> mapperConverter = new MapperConverter<>();
        return mapperConverter.apply(input);
    }

    default <D extends Dto, E extends IEntity> D toDto(E entity) {
        MapperConverter<Input, E, D> mapperConverter = new MapperConverter<>();
        return mapperConverter.apply(entity);
    }

    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "id", expression = "java(map(budgetInput.getId()))")
    @Mapping(target = "name", expression = "java(budgetInput.getName())")
    @Mapping(target = "isDeleted", expression = "java(false)")
    @Mapping(target = "startDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    @Mapping(target = "endDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    @Mapping(source = "categoryId", target = "category", qualifiedBy = NullToCategory.class)
    Budget fromInput(BudgetInput budgetInput, User user, String categoryId);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", expression = "java(budgetInput.getName())")
    @Mapping(target = "isDeleted", expression = "java(false)")
    @Mapping(target = "startDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    @Mapping(target = "endDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    RecurringBudget fromInputToRecurringBudget(BudgetInput budgetInput, User user, Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryDto categoryDto, @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryInput categoryInput, @MappingTarget Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "startDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    @Mapping(target = "endDate", qualifiedBy = NullLocaDateToLocalDateTime.class)
    Budget partialUpdate(BudgetInput budgetInput, @MappingTarget Budget budget);

    default UUID map(String value) {
        if (value == null) {
            return null;
        }
        return UUID.fromString(value);
    }

    default String map(UUID value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    ResponsePage<CategoryDto> toDtoPage(ResponsePage<Category> categories);


    @Named("mapCategoryList")
    default List<Category> mapCategoryList(List<Category> source) {
        return source;
    }


    default <T> ResponsePage<T> toResponsePage(Page<T> page) {
        ResponsePage<T> responsePage = new ResponsePage<>();
        responsePage.setItems(page.toList());
        responsePage.setPageNo(page.getNumber() + 1);
        responsePage.setLast(page.isLast());
        responsePage.setPageSize(page.getSize());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setTotalElements(page.getTotalElements());

        return responsePage;
    }

    default <T> ResponsePage<T> toResponsePage(Page<T> page, Class<T> clazz) {
        ResponsePage<T> responsePage = new ResponsePage<>();

        if (clazz.isNestmateOf(Budget.class)) {
            List<Budget> budgets = (List<Budget>) page.toList();
            responsePage.setItems((List<T>) toBudgetListDto(budgets));
        }

        responsePage.setPageNo(page.getNumber() + 1);
        responsePage.setLast(page.isLast());
        responsePage.setPageSize(page.getSize());
        responsePage.setTotalPages(page.getTotalPages());
        responsePage.setTotalElements(page.getTotalElements());
        return responsePage;
    }

}