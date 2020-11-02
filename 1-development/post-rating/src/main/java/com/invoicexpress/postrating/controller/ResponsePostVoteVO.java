package com.invoicexpress.postrating.controller;

import com.invoicexpress.postrating.entity.Post;

public class ResponsePostVoteVO extends ResponseTextVO {
    private Post post;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
