package com.taskflowdev.financeiro.transaction;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class FinancialTransactionController {
    private final FinancialTransactionService service;
    public FinancialTransactionController(FinancialTransactionService service) { this.service = service; }

    @PostMapping("/income")
    public FinancialTransaction createIncome(Authentication auth, @RequestParam @DecimalMin("0.01") BigDecimal amount, @RequestParam @NotBlank String description) {
        return service.record(auth.getName(), TransactionType.INCOME, amount, description);
    }

    @PostMapping("/expense")
    public FinancialTransaction createExpense(Authentication auth, @RequestParam @DecimalMin("0.01") BigDecimal amount, @RequestParam @NotBlank String description) {
        return service.record(auth.getName(), TransactionType.EXPENSE, amount, description);
    }

    @GetMapping
    public List<FinancialTransaction> list(Authentication auth) {
        return service.listByEmail(auth.getName());
    }
}
