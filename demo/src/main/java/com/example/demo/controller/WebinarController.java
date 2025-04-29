package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.Webinar;
import com.example.demo.service.UserService;
import com.example.demo.service.WebinarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/webinars")
public class WebinarController {

    @Autowired
    private WebinarService webinarService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Webinar> getAllWebinars() {
        return webinarService.getAllWebinars();
    }

    @GetMapping("/{id}")
    public Optional<Webinar> getWebinarById(@PathVariable Long id) {
        return webinarService.getWebinarById(id);
    }

    @PostMapping
    public Webinar createWebinar(@RequestBody Webinar webinar, @RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null || (!user.getRole().equals("INSTRUCTOR") && !user.getRole().equals("ADMIN"))) {
            return null;
        }
        return webinarService.saveWebinar(webinar);
    }

    @DeleteMapping("/{id}")
    public void deleteWebinarById(@PathVariable Long id,@RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null ||!user.getRole().equals("ADMIN")) {
            System.out.println("Unauthorized.");
        }
        webinarService.deleteWebinarById(id);
    }
}
