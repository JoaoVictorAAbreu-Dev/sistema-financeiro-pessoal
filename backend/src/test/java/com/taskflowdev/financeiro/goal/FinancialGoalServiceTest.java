package com.taskflowdev.financeiro.goal;

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
class FinancialGoalServiceTest {
    @Mock FinancialGoalRepository repository;
    @Mock UserRepository userRepository;

    @Test
    void createPersistsGoal() {
        when(userRepository.findByEmail("a@a.com")).thenReturn(Optional.of(new UserAccount("a@a.com", "x")));
        when(repository.save(any(FinancialGoal.class))).thenAnswer(invocation -> invocation.getArgument(0));
        FinancialGoalService service = new FinancialGoalService(repository, userRepository);
        FinancialGoal goal = service.create("a@a.com", "emergency fund", BigDecimal.valueOf(1000));
        assertEquals("emergency fund", goal.getName());
    }

    @Test
    void listReturnsUserGoals() {
        when(repository.findByOwner_Email("a@a.com")).thenReturn(List.of());
        FinancialGoalService service = new FinancialGoalService(repository, userRepository);
        assertEquals(0, service.list("a@a.com").size());
    }
}
