package com.invoicexpress.postrating.service;

import com.invoicexpress.postrating.entity.Post;


public class ScorePostDTO {
    private Post post;
    private double score;

    public ScorePostDTO(Post post, double score)  {
        this.post = post;
        this.score = score;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

}
