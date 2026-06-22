package com.taskflowdev.financeiro.dashboard;

import com.taskflowdev.financeiro.dashboard.dto.DashboardResponse;
import com.taskflowdev.financeiro.goal.FinancialGoalService;
import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final FinancialTransactionService transactionService;
    private final FinancialGoalService goalService;
    public DashboardController(FinancialTransactionService transactionService, FinancialGoalService goalService) { this.transactionService = transactionService; this.goalService = goalService; }
    @GetMapping
    public DashboardResponse summary(Authentication auth) {
        return new DashboardResponse(transactionService.netBalance(auth.getName()), goalService.list(auth.getName()).size());
    }
}
