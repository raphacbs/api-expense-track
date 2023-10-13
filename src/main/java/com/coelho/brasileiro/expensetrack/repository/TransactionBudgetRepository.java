package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.TransactionBudget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionBudgetRepository extends JpaRepository<TransactionBudget, UUID> {
}