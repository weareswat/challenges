package com.ix.challenge.topic;

class PostNotFoundException extends RuntimeException  {
    PostNotFoundException(int postId) {
        super("Could not find post " + postId);
    }
}
