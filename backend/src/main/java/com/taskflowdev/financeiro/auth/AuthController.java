package com.taskflowdev.financeiro.auth;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/login")
    public String login(@Valid @RequestBody String body) { return "todo"; }
}
