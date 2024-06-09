package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.RecurringTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface RecurringTransactionRepository extends JpaRepository<RecurringTransaction, UUID> {

    @Query("SELECT rt FROM RecurringTransaction rt WHERE rt.isActive = true AND rt.isDeleted = false AND rt.startDate < :startDate AND rt.endDate > :endDate AND rt.lastProcessing < :lastProcessing")
    List<RecurringTransaction> findAllByIsActiveTrueAndStartDateBeforeAndEndDateAfterAndLastProcessingBefore(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("lastProcessing") LocalDateTime lastProcessing
    );

}