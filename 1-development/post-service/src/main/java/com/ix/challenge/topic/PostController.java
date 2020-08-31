package com.ix.challenge.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return service.getAll();
    }

    @PostMapping("/posts")
    public Post newPost(@RequestBody Post newPost) {
        return service.savePost(newPost);
    }

    @PutMapping("/upvote/{postId}")
    public Post upVote(@PathVariable int postId) {
        return service.upVote(postId);
    }

    @PutMapping("/downvote/{postId}")
    public Post downVote(@PathVariable int postId) {
        return service.downVote(postId);
    }
}
