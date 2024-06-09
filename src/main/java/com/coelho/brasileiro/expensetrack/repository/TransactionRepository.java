package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByGroupIdAndDueDate(UUID groupId, LocalDate dueDate);

    @Query("SELECT t FROM Transaction t WHERE t.isDeleted = false AND t.dueDate BETWEEN :startDate AND :endDate AND (:description IS NULL OR lower(t.description) LIKE lower(concat('%', :description,'%')))")
    List<Transaction> findByPeriodAndDescription(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("description") String description
    );
}