package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.PaymentMethod;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, UUID> {
    Optional<PaymentMethod> findByName(String name);

    @Query("""
            select p from PaymentMethod p
            where (upper(p.name) like upper(concat('%', ?1, '%')) or upper(p.description) like upper(concat('%', ?2, '%'))) and p.user.id = ?3""")
    Page<PaymentMethod> findByNameOrDescription(String name, String description, UUID id, Pageable pageable);

    @Query("select p from PaymentMethod p where p.user.id = ?1")
    Page<PaymentMethod> findByUserId(UUID id, Pageable pageable);



}