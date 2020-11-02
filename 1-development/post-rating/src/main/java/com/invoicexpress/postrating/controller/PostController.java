package com.invoicexpress.postrating.controller;

import com.invoicexpress.postrating.Enum.PostEnum;
import com.invoicexpress.postrating.Enum.VoteEnum;
import com.invoicexpress.postrating.entity.Post;
import com.invoicexpress.postrating.exceptions.InvalidPostIdException;
import com.invoicexpress.postrating.service.PostService;
import com.invoicexpress.postrating.service.ScorePostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/posts/")
    public ResponsePostVO getAllPosts(){
        ResponsePostVO response = new ResponsePostVO();
        List<ScorePostDTO> listOfAllPosts = postService.getAllPosts();
        response.setMessage(PostEnum.SEARCH_SUCCESSFULLY_CONDUCTED.getShdes());
        response.setListOfPosts(listOfAllPosts);

        return response;
    }

    @PostMapping("/downvote/")
    public ResponsePostVoteVO postDownVote(@RequestParam int post_id){
        ResponsePostVoteVO response = new ResponsePostVoteVO();

        try {
            response.setPost(postService.updatePostVotes(post_id, VoteEnum.DOWN_VOTE));
            response.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());

        } catch (InvalidPostIdException e) {
            response.setMessage(PostEnum.INVALID_POST_ID.getShdes());
        }
        return response;
    }

    @PostMapping("/upvote/")
    public ResponsePostVoteVO PostUpVote(@RequestParam(name = "post_id") int post_id){
        ResponsePostVoteVO response = new ResponsePostVoteVO();

        try {
             response.setPost(postService.updatePostVotes(post_id, VoteEnum.UP_VOTE));
            response.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());
        } catch (InvalidPostIdException e) {
            response.setMessage(PostEnum.INVALID_POST_ID.getShdes());
        }
        return response;
    }
}
