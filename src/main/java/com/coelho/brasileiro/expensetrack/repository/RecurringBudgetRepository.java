package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.RecurringBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RecurringBudgetRepository extends JpaRepository<RecurringBudget, UUID> {
    @Query("SELECT rb FROM RecurringBudget rb WHERE rb.endDate IS NULL AND rb.isDeleted = false")
    List<RecurringBudget> findActiveRecurringBudgets();
}