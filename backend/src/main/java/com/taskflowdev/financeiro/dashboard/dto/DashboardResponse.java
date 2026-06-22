package com.taskflowdev.financeiro.dashboard.dto;

import java.math.BigDecimal;

public record DashboardResponse(
        BigDecimal balance,
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        int goalsCount,
        long activeGoalsCount,
        BigDecimal averageGoalProgressPercentage
) {}
