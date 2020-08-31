package com.ix.challenge.topic;

import com.ix.challenge.topic.Post;

import java.util.List;

public class PostResponse {
    //Result Message
    private String message;

    //Affected Posts in case of a db change, returned posts in case of a find
    private List<Post> posts;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
