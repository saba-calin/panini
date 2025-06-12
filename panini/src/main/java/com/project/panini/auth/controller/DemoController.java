package com.project.panini.auth.controller;

import com.project.panini.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/no-auth")
    public ResponseEntity<String> noAuth() {
        return ResponseEntity.status(HttpStatus.OK).body("wassup");
    }

    @GetMapping("/secured")
    public ResponseEntity<String> secured() {
        return ResponseEntity.status(HttpStatus.OK).body("secured");
    }

    @GetMapping("/username")
    public ResponseEntity<String> username() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.status(HttpStatus.OK).body("Username: " + user.getUsername());
    }
}
