package com.taskflowdev.financeiro.dashboard.dto;

import java.math.BigDecimal;

public record DashboardResponse(BigDecimal balance, int goalsCount) {}
