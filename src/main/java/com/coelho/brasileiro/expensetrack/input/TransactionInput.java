package com.coelho.brasileiro.expensetrack.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@AllArgsConstructor
@Builder
public class TransactionInput extends AInput{
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "id must be a valid UUID or null")
    private String id;
    @NotNull(message = "Type is mandatory")
    @Pattern(regexp = "^[ER]$", message = "Type must be 'E' or 'R'")
    private String type;
    @NotNull(message = "Description is mandatory")
    private String description;
    @PositiveOrZero(message = "Value must be positive or zero")
    @NotNull(message = "Value is mandatory")
    private Double value;
    @PositiveOrZero(message = "Total value must be positive or zero")
    @NotNull(message = "Total value is mandatory")
    private Double totalValue;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "PaymentDate must be in the format YYYY-MM-DD")
    //@PastOrPresent(message = "PaymentDate must be in the past or present")
    private String paymentDate;
    @NotNull(message = "Due date is mandatory")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Due date must be in the format YYYY-MM-DD")
    private String dueDate;
    @NotNull(message = "Installments is mandatory")
    @Positive(message = "Installments must be positive")
    private Integer installments;
    @NotNull(message = "Current installments is mandatory")
    @Positive(message = "Current installments must be positive")
    private Integer currentInstallments;
    @NotNull(message = "Is paid is mandatory")
    private String merchant;
    private String tags;
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "Category id must be a valid UUID or null")
    private String categoryId;
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "Payment id must be a valid UUID or null")
    private String paymentMethodId;
    @Pattern(regexp = "^(ANNUAL|MONTHLY|WEEKLY|DAILY|BIWEEKLY)$", message = "Type must be 'A', 'M', 'W', 'D', or 'BW'")
    private String frequency;
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "Budget id must be a valid UUID or null")
    private String budgetId;
    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}|null", message = "MoneyBox id must be a valid UUID or null")
    private String moneyBoxId;
}
