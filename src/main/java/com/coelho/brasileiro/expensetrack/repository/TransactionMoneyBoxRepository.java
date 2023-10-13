package com.coelho.brasileiro.expensetrack.repository;

import com.coelho.brasileiro.expensetrack.model.TransactionMoneyBox;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionMoneyBoxRepository extends JpaRepository<TransactionMoneyBox, UUID> {
}