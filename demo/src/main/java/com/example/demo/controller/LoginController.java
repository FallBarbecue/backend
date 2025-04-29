package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        String token = String.valueOf(userService.login(email, password));

        Map<String, String> response = new HashMap<>();
        if (token.equals("Invalid credentials")) {
            response.put("message", "Invalid credentials");
            return ResponseEntity.status(401).body(response);
        }

        response.put("message", "Login successful");
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
