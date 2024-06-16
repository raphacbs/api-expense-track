package com.coelho.brasileiro.expensetrack.filter;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Getter
public class TransactionFilterRequest extends BaseFilterRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String description;
    private UUID categoryId;
    private UUID budgetId;

    public TransactionFilterRequest(Map<String, String> allParams) {
        super(allParams);
    }

    @Override
    public String getSortBy() {
        return "description";
    }
}