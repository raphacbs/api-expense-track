package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.MoneyBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MoneyBoxRepository extends JpaRepository<MoneyBox, UUID> {
}