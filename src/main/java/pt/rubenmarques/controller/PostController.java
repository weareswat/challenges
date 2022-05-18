package pt.rubenmarques.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.rubenmarques.domain.dtos.post.PostDTO;
import pt.rubenmarques.service.post.interfaces.IPostService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PostController {

    private final IPostService postService;

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        log.info("[REQUEST] PostController:getAllPosts");
        var response = this.postService.getPostsOrderedByRatingDESC();
        log.info("[RESPONSE] PostController:upVotePostById response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upvote/{post_id}")
    public ResponseEntity<PostDTO> upVotePostById(@PathVariable("post_id") final Long postId) {
        log.info("[REQUEST] PostController:upVotePostById {}", postId);
        var response = this.postService.upVotePostById(postId);
        log.info("[RESPONSE] PostController:upVotePostById response: {}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/downvote/{post_id}")
    public ResponseEntity<PostDTO> downVotePostById(@PathVariable("post_id") final Long postId) {
        log.info("[REQUEST] PostController:downVotePostById");
        var response = this.postService.downVotePostById(postId);
        log.info("[RESPONSE] PostController:downVotePostById response: {}", response);
        return ResponseEntity.ok(response);
    }

}
