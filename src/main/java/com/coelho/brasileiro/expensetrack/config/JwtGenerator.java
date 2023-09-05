package com.coelho.brasileiro.expensetrack.config;


import com.coelho.brasileiro.expensetrack.dto.TokenDto;
import com.coelho.brasileiro.expensetrack.dto.UserDTO;

public interface JwtGenerator {

    TokenDto generateToken(UserDTO user);
}
