package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.filter.TransactionFilterRequest;
import com.coelho.brasileiro.expensetrack.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    Optional<Transaction> findByGroupIdAndDueDate(UUID groupId, LocalDate dueDate);

    @Query("SELECT t FROM Transaction t WHERE t.isDeleted = false AND t.dueDate BETWEEN :#{#filterRequest.startDate} AND :#{#filterRequest.endDate} AND (:#{#filterRequest.description} IS NULL OR lower(t.description) LIKE lower(concat('%', :#{#filterRequest.description},'%')))")
    Page<Transaction> findByPeriodAndDescription(
            @Param("filterRequest") TransactionFilterRequest filterRequest,
            Pageable pageable
    );
}