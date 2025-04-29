package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Role;
import com.example.demo.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String secretKey = "your-secret-key";


    public String register(User user) {
        if (user.getRole() == null) {
            user.setRole(Role.GUEST);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered with role: " + user.getRole();
    }


    public Optional login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && encoder.matches(password, userOpt.get().getPassword())) {
            return userOpt;
        }
        return Optional.empty();
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }


    public boolean validateToken(String token, String username) {
        String user = extractUsername(token);
        return (user.equals(username) && !isTokenExpired(token));
    }


    private String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    private Date extractExpiration(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
    }


    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void updateProfilePicture(Long userId, byte[] pictureData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        user.setProfilePicture(pictureData);
        userRepository.save(user);
    }
    public boolean isAdmin(User user) {
        return user.getRole() == Role.ADMIN;
    }
    public boolean isIntructor(User user) {
        return user.getRole() == Role.INSTRUCTOR;
    }
    public boolean isStudent(User user) {
        return user.getRole() == Role.STUDENT;
    }
}
