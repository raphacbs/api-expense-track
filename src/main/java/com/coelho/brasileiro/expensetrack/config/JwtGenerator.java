package com.coelho.brasileiro.expensetrack.config;


import com.coelho.brasileiro.expensetrack.dto.TokenDto;
import com.coelho.brasileiro.expensetrack.dto.UserDto;

public interface JwtGenerator {

    TokenDto generateToken(UserDto user);
}
