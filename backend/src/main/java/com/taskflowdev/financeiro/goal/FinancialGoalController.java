package com.taskflowdev.financeiro.goal;

import com.taskflowdev.financeiro.goal.dto.FinancialGoalRequest;
import com.taskflowdev.financeiro.goal.dto.FinancialGoalResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/goals")
public class FinancialGoalController {
    private final FinancialGoalService service;
    public FinancialGoalController(FinancialGoalService service) { this.service = service; }
    @PostMapping
    public FinancialGoalResponse create(Authentication auth, @Valid @RequestBody FinancialGoalRequest request) {
        return FinancialGoalMapper.toResponse(service.create(auth.getName(), request.name(), request.targetAmount()));
    }
    @GetMapping
    public List<FinancialGoalResponse> list(Authentication auth) { return service.list(auth.getName()).stream().map(FinancialGoalMapper::toResponse).toList(); }
}
