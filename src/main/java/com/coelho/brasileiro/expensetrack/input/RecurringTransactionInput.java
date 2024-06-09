package com.coelho.brasileiro.expensetrack.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecurringTransactionInput {
    private String id;
    private String type;
    private String description;
    private Double value;
    private String frequency;
    private String merchant;
    private String tags;
    @JsonProperty("is_fixed_value")
    private Boolean isFixedValue;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("last_processing")
    private String lastProcessing;
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    @JsonProperty("due_date")
    private String dueDate;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("category_id")
    private String categoryId;
    @JsonProperty("money_box_id")
    private String moneyBoxId;
    @JsonProperty("budget_id")
    private String budgetId;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("last_due_date")
    private String lastDueDate;

}
