package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

import static java.lang.constant.ConstantDescs.NULL;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course, @RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null || !user.getRole().equals("INSTRUCTOR") && !user.getRole().equals("ADMIN")) {
            return (Course) NULL;
        }
        return courseService.saveCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id, @RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null || !user.getRole().equals("ADMIN")) {
            System.out.println("Unauthorized delete course");
        }
        courseService.deleteCourse(id);
    }
}
