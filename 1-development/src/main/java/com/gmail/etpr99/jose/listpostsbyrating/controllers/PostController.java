package com.gmail.etpr99.jose.listpostsbyrating.controllers;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import com.gmail.etpr99.jose.listpostsbyrating.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    /**
     * The service for {@link Post} objects.
     */
    private final PostService postService;

    /**
     * Creates a new instance of {@link PostController}.
     *
     * @param postService The service for {@link Post} objects.
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Gets all {@link Post} objects.
     *
     * @return All {@link Post} objects.
     */
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAll() {
        return new ResponseEntity<>(postService.getAll(), HttpStatus.OK);
    }

    /**
     * Inserts a new {@link Post} object.
     *
     * @param post The {@link Post} object to insert.
     * @return The inserted {@link Post} object.
     */
    @PostMapping("/posts")
    public ResponseEntity<Post> insertPost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.insertPost(post), HttpStatus.OK);
    }

    /**
     * Updates an existing {@link Post} object.
     *
     * @param post The {@link Post} object to update.
     * @return The updated {@link Post} object.
     */
    @PutMapping("/posts")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post) {
        return new ResponseEntity<>(postService.updatePost(post), HttpStatus.OK);
    }

    /**
     * Deletes an existing {@link Post} object.
     *
     * @param post The {@link Post} object to delete.
     */
    @PatchMapping("/posts")
    public ResponseEntity<String> deletePost(@Valid @RequestBody Post post) {
        postService.deletePost(post);
        return new ResponseEntity<>("Post with ID " + post.getId() + " deleted.", HttpStatus.OK);
    }

    /**
     * Gets the upvotes of a {@link Post} with the given ID.
     *
     * @param postId The ID of the {@link Post} to get the upvotes of.
     * @return The upvotes of the {@link Post} with the given ID.
     */
    @GetMapping("/upvote/{postId}")
    public ResponseEntity<Long> getPostUpvotes(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostUpvotes(postId), HttpStatus.OK);
    }

    /**
     * Upvotes a {@link Post} with the given ID.
     *
     * @param postId The ID of the {@link Post} to upvote.
     */
    @PostMapping("/upvote/{postId}")
    public ResponseEntity<String> upvotePost(@PathVariable Long postId) {
        postService.upvotePost(postId);
        return new ResponseEntity<>("Post with ID " + postId + " upvoted.", HttpStatus.OK);
    }

    /**
     * Gets the downvotes of a {@link Post} with the given ID.
     *
     * @param postId The ID of the {@link Post} to get the downvotes of.
     * @return The downvotes of the {@link Post} with the given ID.
     */
    @GetMapping("/downvote/{postId}")
    public ResponseEntity<Long> getPostDownvotes(@PathVariable Long postId) {
        return new ResponseEntity<>(postService.getPostDownvotes(postId), HttpStatus.OK);
    }

    /**
     * Downvotes a {@link Post} with the given ID.
     *
     * @param postId The ID of the {@link Post} to downvote.
     */
    @PostMapping("/downvote/{postId}")
    public ResponseEntity<String> downvotePost(@PathVariable Long postId) {
        postService.downvotePost(postId);
        return new ResponseEntity<>("Post with ID " + postId + " downvoted.", HttpStatus.OK);
    }
}
