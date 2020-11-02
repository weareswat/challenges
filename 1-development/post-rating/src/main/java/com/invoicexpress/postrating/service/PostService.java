package com.invoicexpress.postrating.service;

import com.invoicexpress.postrating.Enum.VoteEnum;
import com.invoicexpress.postrating.entity.Post;
import com.invoicexpress.postrating.exceptions.InvalidPostIdException;
import com.invoicexpress.postrating.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post updatePostVotes(int post_id, VoteEnum vote) throws InvalidPostIdException {

        Post post = postRepository.findById(post_id).orElse(null);
        if (post == null){
            throw new InvalidPostIdException();

        }
        if (vote == VoteEnum.UP_VOTE) {
            post.setUpVotes(post.getUpVotes() + 1);
        } else {
            post.setDownVotes(post.getDownVotes() + 1);
        }
        return postRepository.save(post);
    }

    public double calculateScoreOfPost(Post post){
        float total = post.getDownVotes() + post.getUpVotes();
        float upVotesOutTotal = post.getUpVotes()/total;
        float confidentiality = 1.96f;

        double score = (upVotesOutTotal + confidentiality*confidentiality/(2*total) - confidentiality * Math.sqrt(upVotesOutTotal * (1-upVotesOutTotal)
                +confidentiality*confidentiality/(4*total))/total) / (1+confidentiality*confidentiality/total);
        return score;
    }

    public List<ScorePostDTO> getAllPosts(){
        List<Post> listOfPosts = postRepository.findAll();
        List<ScorePostDTO> list = new ArrayList<>();
        listOfPosts.stream().forEach(p -> list.add(new ScorePostDTO(p, calculateScoreOfPost(p))));
        list.sort(((o1, o2) -> Double.compare(o2.getScore(), o1.getScore())));

        return list;
    }
}
