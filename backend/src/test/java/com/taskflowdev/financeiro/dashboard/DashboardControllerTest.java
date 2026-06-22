package com.taskflowdev.financeiro.dashboard;

import com.taskflowdev.financeiro.dashboard.dto.DashboardResponse;
import com.taskflowdev.financeiro.goal.FinancialGoal;
import com.taskflowdev.financeiro.goal.FinancialGoalService;
import com.taskflowdev.financeiro.transaction.FinancialTransaction;
import com.taskflowdev.financeiro.transaction.FinancialTransactionService;
import com.taskflowdev.financeiro.transaction.TransactionType;
import com.taskflowdev.financeiro.user.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {
    @Test
    void summaryReturnsAggregatedFinancialMetrics() {
        FinancialTransactionService transactionService = new FinancialTransactionService(null, null) {
            @Override
            public List<FinancialTransaction> listByEmail(String email) {
                UserAccount owner = new UserAccount(email, "x");
                return List.of(
                        new FinancialTransaction(owner, TransactionType.INCOME, BigDecimal.valueOf(1000), "salary", java.time.Instant.now()),
                        new FinancialTransaction(owner, TransactionType.EXPENSE, BigDecimal.valueOf(250), "market", java.time.Instant.now())
                );
            }
        };
        FinancialGoalService goalService = new FinancialGoalService(null, null) {
            @Override
            public List<FinancialGoal> list(String email) {
                return List.of(new FinancialGoal(new UserAccount(email, "x"), "trip", BigDecimal.valueOf(1000)));
            }
        };
        DashboardController controller = new DashboardController(new DashboardService(transactionService, goalService));
        Authentication authentication = new Authentication() {
            @Override public String getName() { return "user@example.com"; }
            @Override public Object getCredentials() { return null; }
            @Override public Object getDetails() { return null; }
            @Override public Object getPrincipal() { return null; }
            @Override public boolean isAuthenticated() { return true; }
            @Override public void setAuthenticated(boolean isAuthenticated) { }
            @Override public java.util.Collection<? extends GrantedAuthority> getAuthorities() { return List.of(); }
        };
        DashboardResponse response = controller.summary(authentication);
        assertEquals(0, BigDecimal.valueOf(750).compareTo(response.balance()));
        assertEquals(0, BigDecimal.valueOf(1000).compareTo(response.totalIncome()));
        assertEquals(0, BigDecimal.valueOf(250).compareTo(response.totalExpenses()));
        assertEquals(1, response.goalsCount());
        assertEquals(1, response.activeGoalsCount());
    }
}
