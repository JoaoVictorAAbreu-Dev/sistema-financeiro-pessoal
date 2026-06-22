package com.taskflowdev.financeiro.transaction;

import com.taskflowdev.financeiro.user.UserAccount;
import com.taskflowdev.financeiro.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FinancialTransactionServiceTest {
    @Mock FinancialTransactionRepository repository;
    @Mock UserRepository userRepository;

    @Test
    void netBalanceTreatsExpenseAsNegative() {
        FinancialTransaction income = new FinancialTransaction(new UserAccount("a@a.com", "x"), TransactionType.INCOME, BigDecimal.valueOf(100), "income", java.time.Instant.now());
        FinancialTransaction expense = new FinancialTransaction(new UserAccount("a@a.com", "x"), TransactionType.EXPENSE, BigDecimal.valueOf(40), "expense", java.time.Instant.now());
        when(repository.findByOwner_EmailOrderByCreatedAtDesc("a@a.com")).thenReturn(List.of(income, expense));
        FinancialTransactionService service = new FinancialTransactionService(repository, userRepository);
        assertEquals(0, BigDecimal.valueOf(60).compareTo(service.netBalance("a@a.com")));
    }

    @Test
    void recordPersistsTransaction() {
        when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.of(new UserAccount("a@a.com", "x")));
        when(repository.save(any(FinancialTransaction.class))).thenAnswer(invocation -> invocation.getArgument(0));
        FinancialTransactionService service = new FinancialTransactionService(repository, userRepository);
        var saved = service.record("a@a.com", TransactionType.INCOME, BigDecimal.TEN, "salary");
        assertEquals(TransactionType.INCOME, saved.getType());
    }
}
