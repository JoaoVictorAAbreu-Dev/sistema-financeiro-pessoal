package com.taskflowdev.financeiro.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record FinancialTransactionRequest(
        @DecimalMin("0.01") BigDecimal amount,
        @NotBlank String description
) {}
