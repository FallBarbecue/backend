package com.example.demo.controller;

import com.example.demo.entity.Forum;
import com.example.demo.service.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @GetMapping
    public List<Forum> getAllPosts() {
        return forumService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<Forum> getPost(@PathVariable Long id) {
        return forumService.getPostById(id);
    }

    @PostMapping
    public Forum createPost(@RequestBody Forum forum) {
        return forumService.createPost(forum);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        forumService.deletePost(id);
    }
}
