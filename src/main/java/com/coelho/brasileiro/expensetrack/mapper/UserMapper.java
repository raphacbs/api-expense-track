package com.coelho.brasileiro.expensetrack.mapper;

import com.coelho.brasileiro.expensetrack.dto.UserDTO;
import com.coelho.brasileiro.expensetrack.model.User;
import com.coelho.brasileiro.expensetrack.dto.request.LoginRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    User toEntity(UserDTO userDTO);

    User toEntity(LoginRequest loginRequest);
    UserDTO toDto(User user);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(UserDTO userDTO, @MappingTarget User user);
}