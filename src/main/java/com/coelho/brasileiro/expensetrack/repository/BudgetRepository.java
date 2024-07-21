package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.Budget;
import com.coelho.brasileiro.expensetrack.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BudgetRepository extends JpaRepository<Budget, UUID> {

    @Query("SELECT b FROM Budget b " +
            "WHERE b.parentId = :parentId " +
            "AND b.isDeleted = false " +
            "ORDER BY b.startDate DESC")
    List<Budget> findLastBudgetByParentId(
            @Param("parentId") UUID parentId
    );

    Page<Budget> findByStartDateGreaterThanEqualAndEndDateLessThanEqualAndUser(LocalDateTime startDate,
                                                                               LocalDateTime endDate,
                                                                               User user,
                                                                               Pageable pageable);

    Page<Budget> findByStartDateBetweenAndUser(LocalDateTime startDate,
                                               LocalDateTime startDate2,
                                               User user,
                                               Pageable pageable);

    Page<Budget> findByStartDateGreaterThanEqual(LocalDateTime startDate,
                                                 User user,
                                                 Pageable pageable);

    void deleteAllByParentId(UUID parentId);

    Budget findByUserAndId(User userLogged, UUID id);
}