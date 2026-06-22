package com.taskflowdev.financeiro.goal;

import com.taskflowdev.financeiro.user.UserAccount;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "financial_goals")
public class FinancialGoal {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount owner;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal targetAmount;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal currentAmount;
    protected FinancialGoal() {}
    public FinancialGoal(UserAccount owner, String name, BigDecimal targetAmount) { this.owner = owner; this.name = name; this.targetAmount = targetAmount; this.currentAmount = BigDecimal.ZERO; }
    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getTargetAmount() { return targetAmount; }
    public BigDecimal getCurrentAmount() { return currentAmount; }
    public void addAmount(BigDecimal amount) { currentAmount = currentAmount.add(amount); }
}
