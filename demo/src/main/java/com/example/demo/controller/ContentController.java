package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContentController {

    @Autowired private NewsRepository newsRepository;
    @Autowired private CourseRepository courseRepository;
    @Autowired private WebinarRepository webinarRepository;
    @Autowired private ContactMessageRepository contactMessageRepository;

//    @GetMapping("/news")
//    public List<News> getAllNews() {
//        return newsRepository.findAll();
//    }

//    @GetMapping("/courses")
//    public List<Course> getAllCourses() {
//        return courseRepository.findAll();
//    }

//    @GetMapping("/webinars")
//    public List<Webinar> getAllWebinars() {
//        return webinarRepository.findAll();
//    }

//    @PostMapping("/contact")
//    public ContactMessage sendMessage(@RequestBody ContactMessage message) {
//        return contactMessageRepository.save(message);
//    }
}
