package com.coelho.brasileiro.expensetrack.dto;

import lombok.Data;

@Data
public class TransactionResponse extends ResponsePage<TransactionDto> {
    private Double totalBalance;
}
