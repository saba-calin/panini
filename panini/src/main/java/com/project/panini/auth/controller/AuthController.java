package com.project.panini.auth.controller;

import com.project.panini.auth.model.AuthResponse;
import com.project.panini.auth.model.UserLoginRequest;
import com.project.panini.auth.model.UserRegistrationRequest;
import com.project.panini.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<String> getUsername() {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.getUsername());
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserLoginRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.authenticate(request));
    }
}
