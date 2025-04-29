package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/news")

public class NewsController {
    @Autowired private NewsRepository newsRepository;

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
}
