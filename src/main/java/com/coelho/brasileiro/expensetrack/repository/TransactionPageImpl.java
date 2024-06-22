package com.coelho.brasileiro.expensetrack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;

public class TransactionPageImpl<T> extends PageImpl<T> {
    private final BigDecimal balance;

    public TransactionPageImpl(Page<T> page, BigDecimal balance) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
