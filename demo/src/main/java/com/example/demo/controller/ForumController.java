package com.example.demo.controller;

import com.example.demo.entity.Forum;
import com.example.demo.entity.User;
import com.example.demo.service.ForumService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/forums")
public class ForumController {

    @Autowired
    private ForumService forumService;
    @Autowired
    private UserService userService;
    @GetMapping
    public List<Forum> getAllPosts() {
        return forumService.getAllPosts();
    }

    @GetMapping("/{id}")
    public Optional<Forum> getPost(@PathVariable Long id) {
        return forumService.getPostById(id);
    }

    @PostMapping
    public Forum createPost(@RequestBody Forum forum,@RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null || user.getRole().equals("GUEST")) {
            return null;
        }
        return forumService.createPost(forum);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id,@RequestParam String userEmail) {
        User user = userService.getByEmail(userEmail).orElse(null);
        if (user == null || !user.getRole().equals("ADMIN")) {
            System.out.println("<UNK-deletepost>");
        }
        forumService.deletePost(id);
    }
}
