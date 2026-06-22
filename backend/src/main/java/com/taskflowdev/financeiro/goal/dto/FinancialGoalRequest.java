package com.taskflowdev.financeiro.goal.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record FinancialGoalRequest(
        @NotBlank String name,
        @DecimalMin("0.01") BigDecimal targetAmount
) {}
