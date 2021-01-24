package com.mixfar.posts.controllers;

import com.mixfar.posts.model.Post;
import com.mixfar.posts.service.PostService;
import com.mixfar.posts.utils.Utils;
import com.mixfar.posts.utils.enums.ErrorCode;
import com.mixfar.posts.utils.errors.ErrorMessage;
import com.mixfar.posts.utils.errors.FieldErrorMessage;
import com.mixfar.posts.utils.response.DataResponse;
import com.mixfar.posts.utils.response.ErrorResponse;
import com.mixfar.posts.utils.response.Response;
import com.mixfar.posts.validations.PostValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Response> createPost(@RequestBody Post post){
        post.setPostId(null);
        List<FieldErrorMessage> errors = new PostValidation(postService).validCreate(post);
        if (errors != null)
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.INVALID_PARAMETERS, errors), HttpStatus.BAD_REQUEST);

        try{
            postService.save(post);
        }catch (Exception e){
            e.printStackTrace();
            ErrorMessage error = new ErrorMessage("Not Saved", "Failed to save post");
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.FATAL, error), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new DataResponse<>(ErrorCode.CREATED, post), HttpStatus.CREATED);
    }

    @RequestMapping("/posts")
    public ResponseEntity<Response> getPostsByRating(){
        List<Post> posts;
        try {
            posts = postService.findAllByOrderByRatingDesc();
        }catch (Exception e){
            ErrorMessage error = new ErrorMessage("Not found", "Can't found posts");
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.NOT_FOUND, error), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DataResponse<>(ErrorCode.OK, posts), HttpStatus.OK);
    }

    @RequestMapping(value = "/upvote/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Response> upVote(@PathVariable Long id) {
        List<FieldErrorMessage> errors = new PostValidation(postService).validRead(id);
        if (errors != null)
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.NOT_FOUND, errors), HttpStatus.NOT_FOUND);

        Post post = postService.findByPostId(id);
        post.setUpVotes(post.getUpVotes() + 1);
        //Calculate rating
        post.setRating(Utils.calculateRating(post.getUpVotes(), post.getDownVotes(),
                Utils.getDaysUntilNow(post.getPublishDate())));

        errors = new PostValidation(postService).validUpdate(post);
        if (errors != null)
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.INVALID_PARAMETERS, errors), HttpStatus.BAD_REQUEST);

        try {
            postService.save(post);
        }catch (Exception e){
            e.printStackTrace();
            ErrorMessage error = new ErrorMessage("Not Updated", "Failed to update post");
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.FATAL, error), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new DataResponse<>(ErrorCode.UPDATED, post), HttpStatus.OK);
    }

    @RequestMapping(value = "/downvote/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> downVote(@PathVariable Long id) {
        List<FieldErrorMessage> errors = new PostValidation(postService).validRead(id);
        if (errors != null)
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.NOT_FOUND, errors), HttpStatus.NOT_FOUND);

        Post post = postService.findByPostId(id);
        post.setDownVotes(post.getDownVotes() + 1);
        //Calculate rating
        post.setRating(Utils.calculateRating(post.getUpVotes(), post.getDownVotes(),
                Utils.getDaysUntilNow(post.getPublishDate())));

        errors = new PostValidation(postService).validUpdate(post);
        if (errors != null)
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.INVALID_PARAMETERS, errors), HttpStatus.BAD_REQUEST);

        try {
            postService.save(post);
        }catch (Exception e){
            e.printStackTrace();
            ErrorMessage error = new ErrorMessage("Not Updated", "Failed to update post");
            return new ResponseEntity<>(new ErrorResponse<>(ErrorCode.FATAL, error), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(new DataResponse<>(ErrorCode.UPDATED, post), HttpStatus.OK);
    }
}
