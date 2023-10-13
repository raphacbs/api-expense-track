package com.coelho.brasileiro.expensetrack.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record LoginInput(@Email(regexp = ".+[@].+[\\.].+") String email,
                         @NotNull(message = "Password is mandatory") String password) implements Input {
}
