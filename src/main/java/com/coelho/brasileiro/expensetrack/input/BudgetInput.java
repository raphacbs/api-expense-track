package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
public class BudgetInput extends AInput {
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "id must be a valid UUID or null")
    private String id;
    @Pattern(regexp = "^(ANNUAL|MONTHLY|WEEKLY|DAILY|BIWEEKLY)$", message = "Type must be 'A', 'M', 'W', 'D', or 'BW'")
    private String frequency;
    //  @NotNull(message = "endDate is required")
    private LocalDate endDate;
    @NotNull(message = "startDate is required")
    private LocalDate startDate;
    @NotNull(message = "amount is required")
    private double amount;
    @NotNull(message = "name is required")
    private String name;
    @NotNull(message = "notes is required")
    private String notes;
    @NotNull(message = "categoryId is required")
    private String categoryId;
}