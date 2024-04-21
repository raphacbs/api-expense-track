package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByGroupIdAndDueDate(UUID groupId, LocalDate dueDate);
}