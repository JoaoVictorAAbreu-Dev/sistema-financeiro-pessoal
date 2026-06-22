package com.taskflowdev.financeiro.transaction;

import com.taskflowdev.financeiro.transaction.dto.FinancialTransactionResponse;

public final class FinancialTransactionMapper {
    private FinancialTransactionMapper() {}

    public static FinancialTransactionResponse toResponse(FinancialTransaction tx) {
        return new FinancialTransactionResponse(tx.getId(), tx.getType(), tx.getAmount(), tx.getDescription(), tx.getCreatedAt());
    }
}
