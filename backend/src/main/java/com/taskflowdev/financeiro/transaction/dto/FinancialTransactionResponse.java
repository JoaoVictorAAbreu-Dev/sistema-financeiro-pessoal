package com.taskflowdev.financeiro.transaction.dto;

import com.taskflowdev.financeiro.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record FinancialTransactionResponse(
        UUID id,
        TransactionType type,
        BigDecimal amount,
        String description,
        Instant createdAt
) {}
