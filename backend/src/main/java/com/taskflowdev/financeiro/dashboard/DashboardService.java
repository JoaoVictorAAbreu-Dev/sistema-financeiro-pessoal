package com.taskflowdev.financeiro.dashboard;

import com.taskflowdev.financeiro.dashboard.dto.DashboardResponse;
import com.taskflowdev.financeiro.goal.FinancialGoal;
import com.taskflowdev.financeiro.goal.FinancialGoalService;
import com.taskflowdev.financeiro.transaction.FinancialTransaction;
import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import com.taskflowdev.financeiro.transaction.TransactionType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class DashboardService {
    private final FinancialTransactionService transactionService;
    private final FinancialGoalService goalService;

    public DashboardService(FinancialTransactionService transactionService, FinancialGoalService goalService) {
        this.transactionService = transactionService;
        this.goalService = goalService;
    }

    @Transactional(readOnly = true)
    public DashboardResponse summary(String email) {
        var transactions = transactionService.listByEmail(email);
        var goals = goalService.list(email);

        BigDecimal totalIncome = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.INCOME)
                .map(FinancialTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = transactions.stream()
                .filter(transaction -> transaction.getType() == TransactionType.EXPENSE)
                .map(FinancialTransaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalIncome.subtract(totalExpenses);
        long activeGoals = goals.stream()
                .filter(goal -> goal.getCurrentAmount().compareTo(goal.getTargetAmount()) < 0)
                .count();

        BigDecimal goalProgress = averageGoalProgress(goals);
        return new DashboardResponse(balance, totalIncome, totalExpenses, goals.size(), activeGoals, goalProgress);
    }

    private BigDecimal averageGoalProgress(java.util.List<FinancialGoal> goals) {
        if (goals.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal totalProgress = goals.stream()
                .map(goal -> goal.getCurrentAmount()
                        .multiply(BigDecimal.valueOf(100))
                        .divide(goal.getTargetAmount(), 2, RoundingMode.HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalProgress.divide(BigDecimal.valueOf(goals.size()), 2, RoundingMode.HALF_UP);
    }
}
