package com.coelho.brasileiro.expensetrack.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record UserUpdate(@Email(regexp = ".+[@].+[\\.].+") String email,
                         @NotNull(message = "FirstName is mandatory") String firstName,
                         @NotNull(message = "LastName is mandatory") String lastName
                          ) implements Input {
}
