package com.hp.posts.controller;

import com.hp.posts.model.PostModel;
import com.hp.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<PostModel>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }
}
