package com.taskflowdev.financeiro.transaction;

import com.taskflowdev.financeiro.user.UserAccount;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "financial_transactions")
public class FinancialTransaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount owner;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Instant createdAt;

    protected FinancialTransaction() {}
    public FinancialTransaction(UserAccount owner, TransactionType type, BigDecimal amount, String description, Instant createdAt) {
        this.owner = owner; this.type = type; this.amount = amount; this.description = description; this.createdAt = createdAt;
    }
    public UUID getId() { return id; }
    public UserAccount getOwner() { return owner; }
    public TransactionType getType() { return type; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public Instant getCreatedAt() { return createdAt; }
}
