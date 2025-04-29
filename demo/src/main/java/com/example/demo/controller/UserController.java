package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public Optional login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/profile")
    public Optional<User> profile(@RequestHeader("Authorization") String token) {
        String email = token.replace("Bearer ", "");
        return userService.getByEmail(email);
    }
}


//    @PostMapping("/uploadProfilePicture")
//    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
//        try {
//            byte[] bytes = file.getBytes();
//            userService.updateProfilePicture(userId, bytes);
//
//            return "Profile picture uploaded successfully";
//        } catch (IOException e) {
//            return "Error uploading profile picture";
//        }
//    }