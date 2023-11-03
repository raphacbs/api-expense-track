package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserUpdate extends AInput {
    @Email(regexp = ".+[@].+[\\.].+")
    private String email;
    @NotNull(message = "FirstName is mandatory")
    private String firstName;
    @NotNull(message = "LastName is mandatory")
    private String lastName;
}
