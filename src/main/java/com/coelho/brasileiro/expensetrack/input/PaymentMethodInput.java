package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@AllArgsConstructor
public class PaymentMethodInput extends AInput{
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "id must be a valid UUID or null")
    private String id;
    @NotNull(message = "name is required")
    private String name;
    private String description;
}
