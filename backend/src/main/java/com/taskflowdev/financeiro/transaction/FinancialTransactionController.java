package com.taskflowdev.financeiro.transaction;

import com.taskflowdev.financeiro.transaction.dto.FinancialTransactionRequest;
import com.taskflowdev.financeiro.transaction.dto.FinancialTransactionResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class FinancialTransactionController {
    private final FinancialTransactionService service;
    public FinancialTransactionController(FinancialTransactionService service) { this.service = service; }

    @PostMapping("/income")
    public FinancialTransactionResponse createIncome(Authentication auth, @Valid @RequestBody FinancialTransactionRequest request) {
        return FinancialTransactionMapper.toResponse(service.record(auth.getName(), TransactionType.INCOME, request.amount(), request.description()));
    }

    @PostMapping("/expense")
    public FinancialTransactionResponse createExpense(Authentication auth, @Valid @RequestBody FinancialTransactionRequest request) {
        return FinancialTransactionMapper.toResponse(service.record(auth.getName(), TransactionType.EXPENSE, request.amount(), request.description()));
    }

    @GetMapping
    public List<FinancialTransactionResponse> list(Authentication auth) {
        return service.listByEmail(auth.getName()).stream().map(FinancialTransactionMapper::toResponse).toList();
    }
}
