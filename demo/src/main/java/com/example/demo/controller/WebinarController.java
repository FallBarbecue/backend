package com.example.demo.controller;

import com.example.demo.entity.Webinar;
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

    @GetMapping
    public List<Webinar> getAllWebinars() {
        return webinarService.getAllWebinars();
    }

    @GetMapping("/{id}")
    public Optional<Webinar> getWebinarById(@PathVariable Long id) {
        return webinarService.getWebinarById(id);
    }

    @PostMapping
    public Webinar createWebinar(@RequestBody Webinar webinar) {
        return webinarService.saveWebinar(webinar);
    }

    @DeleteMapping("/{id}")
    public void deleteWebinarById(@PathVariable Long id) {
        webinarService.deleteWebinarById(id);
    }
}
