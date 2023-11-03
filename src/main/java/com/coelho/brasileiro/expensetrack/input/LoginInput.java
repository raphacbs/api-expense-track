package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LoginInput extends AInput {
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @NotNull(message = "Password is mandatory")
    private String password;

}
