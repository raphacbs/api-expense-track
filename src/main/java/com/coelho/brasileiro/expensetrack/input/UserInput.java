package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class UserInput extends AInput {

    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @NotNull(message = "Password is mandatory")
    private  String password;
    @NotNull(message = "FirstName is mandatory")
    private String firstName;
    @NotNull(message = "LastName is mandatory")
    private String lastName;

}
