package com.coelho.brasileiro.expensetrack.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public record CategoryInput (
        @NotNull(message = "Name is mandatory") String name,
        @NotNull(message = "Description is mandatory") String description,
        @NotNull(message = "Color is mandatory")
        @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$", message = "Color must be a valid hexadecimal color code (e.g., #RRGGBB)")
        String color,
        @NotNull(message = "Type is mandatory")
        @Pattern(regexp = "^[ER]$", message = "Type must be 'E' or 'R'")
        String type
) implements Input{
}
