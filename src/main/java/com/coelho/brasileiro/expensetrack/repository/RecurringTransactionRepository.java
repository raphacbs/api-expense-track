package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecurringTransactionRepository extends JpaRepository<RecurringTransaction, UUID> {
}