package com.taskflowdev.financeiro.goal.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record FinancialGoalResponse(
        UUID id,
        String name,
        BigDecimal targetAmount,
        BigDecimal currentAmount
) {}
