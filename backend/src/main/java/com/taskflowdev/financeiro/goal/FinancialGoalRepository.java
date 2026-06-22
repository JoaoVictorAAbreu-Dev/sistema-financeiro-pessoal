package com.taskflowdev.financeiro.goal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface FinancialGoalRepository extends JpaRepository<FinancialGoal, UUID> {
    List<FinancialGoal> findByOwner_Email(String email);
}
