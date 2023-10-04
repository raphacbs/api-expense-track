package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.dto.UserDTO;
import com.coelho.brasileiro.expensetrack.dto.request.UserRequest;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", imports = {DateTimeFormatter.class})

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDTO userDTO);

    User toEntity(LoginRequest loginRequest);

    @Mapping(target = "createdAt", expression = "java(user.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME))")
    UserDTO toDto(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDTO userDTO, @MappingTarget User user);

    User toEntity(UserRequest userRequest);
}