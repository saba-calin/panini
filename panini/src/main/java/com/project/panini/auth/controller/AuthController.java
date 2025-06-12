package com.project.panini.auth.controller;

import com.project.panini.auth.model.AuthResponse;
import com.project.panini.auth.model.UserLoginDto;
import com.project.panini.auth.model.UserRegistrationDto;
import com.project.panini.auth.service.AuthService;
import com.project.panini.user.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody @Valid UserRegistrationDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserLoginDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authService.authenticate(request));
    }
}
