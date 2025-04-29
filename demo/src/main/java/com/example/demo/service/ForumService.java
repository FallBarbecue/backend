package com.example.demo.service;

import com.example.demo.entity.Forum;
import com.example.demo.repository.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForumService {

    @Autowired
    private ForumRepository forumRepository;

    public List<Forum> getAllPosts() {
        return forumRepository.findAll();
    }

    public Optional<Forum> getPostById(Long id) {
        return forumRepository.findById(id);
    }

    public Forum createPost(Forum forum) {
        return forumRepository.save(forum);
    }

    public void deletePost(Long id) {
        forumRepository.deleteById(id);
    }
}
