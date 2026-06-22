package com.taskflowdev.financeiro.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FinancialTransactionRepository extends JpaRepository<FinancialTransaction, UUID> {
    List<FinancialTransaction> findByOwner_EmailOrderByCreatedAtDesc(String email);
}
