package com.taskflowdev.financeiro.user;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserAccount {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    protected UserAccount() {}
    public UserAccount(String email, String password) { this.email = email; this.password = password; }
    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}
