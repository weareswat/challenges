package com.hp.posts.controller;

import com.hp.posts.model.PostModel;
import com.hp.posts.repository.PostRepository;
import com.hp.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostModel>> getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @PostMapping("/upvote/{postId}")
    public ResponseEntity<?> increaseVote(@PathVariable Integer postId) {
        Optional<PostModel> optionalPostModel = postRepository.findById(postId);
        if (optionalPostModel.isPresent()) {
            PostModel postModel = optionalPostModel.get();
            double weightPercentage = postService.calculateWeightPercentage(
                    postModel.getVotesUp(),
                    postModel.getVotesDown()+postModel.getVotesUp()
            );
                    
            postModel.setVotesUp(postModel.getVotesUp()+1);
            postModel.setVotesUpPercentage(Integer.toString((int) weightPercentage).concat("%"));
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
            double weightPercentage = postService.calculateWeightPercentage(
                    postModel.getVotesDown(),
                    postModel.getVotesDown()+postModel.getVotesUp()
            );

            postModel.setVotesDown(postModel.getVotesDown()-1);
            postModel.setVotesDownPercentage(Integer.toString((int) weightPercentage).concat("%"));
            postRepository.save(postModel);

            return ResponseEntity.ok(postModel);
        }

        return ResponseEntity.noContent().build();
    }
}
