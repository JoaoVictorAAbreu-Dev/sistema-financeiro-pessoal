package com.taskflowdev.financeiro.goal;

import com.taskflowdev.financeiro.goal.dto.FinancialGoalResponse;

public final class FinancialGoalMapper {
    private FinancialGoalMapper() {}
    public static FinancialGoalResponse toResponse(FinancialGoal goal) {
        return new FinancialGoalResponse(goal.getId(), goal.getName(), goal.getTargetAmount(), goal.getCurrentAmount());
    }
}
