package com.taskflowdev.financeiro.transaction;

import com.taskflowdev.financeiro.user.UserAccount;
import com.taskflowdev.financeiro.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class FinancialTransactionService {
    private final FinancialTransactionRepository repository;
    private final UserRepository userRepository;

    public FinancialTransactionService(FinancialTransactionRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FinancialTransaction record(String email, TransactionType type, BigDecimal amount, String description) {
        UserAccount user = userRepository.findByEmail(email).orElseThrow();
        return repository.save(new FinancialTransaction(user, type, amount, description, Instant.now()));
    }

    @Transactional(readOnly = true)
    public List<FinancialTransaction> listByEmail(String email) {
        return repository.findByOwner_EmailOrderByCreatedAtDesc(email);
    }

    @Transactional(readOnly = true)
    public BigDecimal netBalance(String email) {
        return listByEmail(email).stream().map(tx -> tx.getType() == TransactionType.EXPENSE ? tx.getAmount().negate() : tx.getAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
