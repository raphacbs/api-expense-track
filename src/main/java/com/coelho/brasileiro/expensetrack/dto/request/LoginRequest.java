package com.coelho.brasileiro.expensetrack.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record LoginRequest(@Email(regexp = ".+[@].+[\\.].+") String email,
                           @NotNull(message = "Password is mandatory") String password) implements  InputRequest{
}
