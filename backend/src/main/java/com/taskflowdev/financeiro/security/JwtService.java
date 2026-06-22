package com.taskflowdev.financeiro.security;

import com.taskflowdev.financeiro.config.JwtProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    private final JwtProperties props;
    public JwtService(JwtProperties props) { this.props = props; }
    public String generate(String subject) {
        Instant now = Instant.now();
        return Jwts.builder().subject(subject).issuedAt(Date.from(now)).expiration(Date.from(now.plusSeconds(props.expirationMinutes() * 60))).signWith(key()).compact();
    }
    private SecretKey key() { return Keys.hmacShaKeyFor(props.secret().getBytes(StandardCharsets.UTF_8)); }
}
