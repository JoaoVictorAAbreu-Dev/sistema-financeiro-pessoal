package com.taskflowdev.financeiro.goal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class FinancialGoalController {
    private final FinancialGoalService service;
    public FinancialGoalController(FinancialGoalService service) { this.service = service; }
    @PostMapping
    public FinancialGoal create(Authentication auth, @RequestParam @NotBlank String name, @RequestParam @DecimalMin("0.01") BigDecimal targetAmount) {
        return service.create(auth.getName(), name, targetAmount);
    }
    @GetMapping
    public List<FinancialGoal> list(Authentication auth) { return service.list(auth.getName()); }
}
