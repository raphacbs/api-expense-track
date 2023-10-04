package com.coelho.brasileiro.expensetrack.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record UserRequest(@Email(regexp = ".+[@].+[\\.].+") String email,
                          @NotNull(message = "Password is mandatory") String password,
                          @NotNull(message = "FirstName is mandatory") String firstName,
                          @NotNull(message = "LastName is mandatory") String lastName
                          ) implements InputRequest{
}
