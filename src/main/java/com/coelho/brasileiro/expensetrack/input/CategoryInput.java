package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class CategoryInput extends AInput {
    @NotNull(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Description is mandatory")
    private String description;

    @NotNull(message = "Color is mandatory")
    @Pattern(regexp = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$",
            message = "Color must be a valid hexadecimal color code (e.g., #RRGGBB)")
    private String color;

    @NotNull(message = "Type is mandatory")
    @Pattern(regexp = "^[ER]$", message = "Type must be 'E' or 'R'")
    private String type;

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null",
            message = "id must be a valid UUID or null")
    private String id;
}
