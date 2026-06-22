package com.taskflowdev.financeiro.auth;

import com.taskflowdev.financeiro.auth.dto.LoginRequest;
import com.taskflowdev.financeiro.auth.dto.RegisterRequest;
import com.taskflowdev.financeiro.config.JwtProperties;
import com.taskflowdev.financeiro.security.JwtService;
import com.taskflowdev.financeiro.user.UserAccount;
import com.taskflowdev.financeiro.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock UserRepository userRepository;
    private AuthService service;

    @BeforeEach
    void setUp() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        JwtService jwtService = new JwtService(new JwtProperties("01234567890123456789012345678901", 60));
        service = new AuthService(userRepository, encoder, jwtService);
    }

    @Test
    void registerGeneratesToken() {
        when(userRepository.existsByEmail("user@example.com")).thenReturn(false);
        when(userRepository.save(any(UserAccount.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var response = service.register(new RegisterRequest("user@example.com", "password123"));
        assertNotNull(response.token());
    }

    @Test
    void loginGeneratesToken() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        UserAccount user = new UserAccount("user@example.com", encoder.encode("password123"));
        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(user));
        service = new AuthService(userRepository, encoder, new JwtService(new JwtProperties("01234567890123456789012345678901", 60)));
        var response = service.login(new LoginRequest("user@example.com", "password123"));
        assertNotNull(response.token());
    }
}
