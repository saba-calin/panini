package com.project.panini.auth.service;

import com.project.panini.auth.model.AuthResponse;
import com.project.panini.auth.model.UserLoginRequest;
import com.project.panini.auth.model.UserRegistrationRequest;
import com.project.panini.exception.UsernameAlreadyExistsException;
import com.project.panini.user.Role;
import com.project.panini.user.User;
import com.project.panini.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(UserRegistrationRequest request) {
        checkIfUserExists(request.getUsername());

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        user = this.userRepository.save(user);
        String token = this.jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse authenticate(UserLoginRequest request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        User user = this.userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = this.jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    private void checkIfUserExists(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }
    }
}
