package com.taskflowdev.financeiro.dashboard;

import com.taskflowdev.financeiro.goal.FinancialGoalService;
import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final FinancialTransactionService transactionService;
    private final FinancialGoalService goalService;
    public DashboardController(FinancialTransactionService transactionService, FinancialGoalService goalService) { this.transactionService = transactionService; this.goalService = goalService; }
    @GetMapping
    public Map<String, Object> summary(Authentication auth) {
        BigDecimal balance = transactionService.netBalance(auth.getName());
        return Map.of("balance", balance, "goals", goalService.list(auth.getName()).size());
    }
}
