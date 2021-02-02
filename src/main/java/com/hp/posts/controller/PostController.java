package com.hp.posts.controller;

import antlr.StringUtils;
import com.hp.posts.model.PostModel;
import com.hp.posts.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public ResponseEntity<List<PostModel>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @PostMapping("/upvote/{postId}")
    public ResponseEntity<?> increaseVote(@PathVariable Integer postId) {
        Optional<PostModel> optionalPostModel = postRepository.findById(postId);
        if (optionalPostModel.isPresent()) {
            PostModel postModel = optionalPostModel.get();
            postModel.setVotesUp(postModel.getVotesUp()+1);
            postModel.setVotesUpPercentage(postModel.getVotesUp().toString().substring(0, 1).concat("%"));
            postRepository.save(postModel);

            return ResponseEntity.ok(postModel);
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/downvote/{postId}")
    public ResponseEntity<?> decreaseVote(@PathVariable Integer postId) {
        Optional<PostModel> optionalPostModel = postRepository.findById(postId);
        if (optionalPostModel.isPresent()) {
            PostModel postModel = optionalPostModel.get();
            postModel.setVotesDown(postModel.getVotesDown()-1);
            postModel.setVotesDownPercentage(postModel.getVotesDown().toString().substring(0,1).concat("%"));
            postRepository.save(postModel);

            return ResponseEntity.ok(postModel);
        }

        return ResponseEntity.noContent().build();
    }
}
