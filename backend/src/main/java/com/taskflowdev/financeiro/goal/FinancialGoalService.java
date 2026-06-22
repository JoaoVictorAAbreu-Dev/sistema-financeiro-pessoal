package com.taskflowdev.financeiro.goal;

import com.taskflowdev.financeiro.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinancialGoalService {
    private final FinancialGoalRepository repository;
    private final UserRepository userRepository;
    public FinancialGoalService(FinancialGoalRepository repository, UserRepository userRepository) { this.repository = repository; this.userRepository = userRepository; }
    @Transactional
    public FinancialGoal create(String email, String name, BigDecimal targetAmount) {
        var owner = userRepository.findByEmail(email).orElseThrow();
        return repository.save(new FinancialGoal(owner, name, targetAmount));
    }
    @Transactional(readOnly = true)
    public List<FinancialGoal> list(String email) { return repository.findByOwner_Email(email); }
}
