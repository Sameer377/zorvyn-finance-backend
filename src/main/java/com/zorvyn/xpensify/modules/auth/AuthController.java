package com.zorvyn.xpensify.modules.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sameer Shaikh
 * @date 06-04-2026
 * @description
 */

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Public authentication endpoints — no token required")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Endpoint to authenticate a user and return a signed JWT token")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam @Email(message = "Email must be valid") String email,
            @RequestParam @Size(min = 8, message = "Password must be at least 8 characters") String password) {

        return ResponseEntity.ok(authService.login(email, password));
    }
}
