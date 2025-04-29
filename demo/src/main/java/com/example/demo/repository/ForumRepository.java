package com.example.demo.repository;

import com.example.demo.entity.Forum;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ForumRepository extends JpaRepository<Forum, Long> {
}
