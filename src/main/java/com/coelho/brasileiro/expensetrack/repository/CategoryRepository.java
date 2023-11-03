package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.Category;
import com.coelho.brasileiro.expensetrack.model.TransactionTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByName(String name); // <T, ID>

    Page<Category> findByNameContainingIgnoreCaseAndIsDeletedFalse(Pageable pageable, String name);
    Page<Category> findByDescriptionContainingIgnoreCaseAndIsDeletedFalse(Pageable pageable, String description);

    @Query("SELECT c FROM Category c WHERE (LOWER(c.description) LIKE %:description% OR LOWER(c.name) LIKE %:name%) AND c.isDeleted = :isDeleted")
    Page<Category> findByDescriptionContainingIgnoreCaseOrNameContainingIgnoreCaseAndIsDeleted(Pageable pageable, String description, String name, boolean isDeleted);
    Page<Category> findByTypeAndIsDeletedFalse(Pageable pageable, TransactionTypeEnum type);

    Page<Category> findByIsDeleted(Pageable pageable, boolean isDeleted);
    Page<Category> findAllByIsDeletedFalse(Pageable pageable);
}