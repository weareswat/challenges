package com.Joao_Branco.listpostsbyrating.repositories;

import com.Joao_Branco.listpostsbyrating.domain.Post;

import java.util.Collection;

public interface PostRepository {

    Integer create(int upvotes, int downvotes, String postBody);

    Post findByPostId(int postId);

    void incrementUpvotes(int postId);

    void incrementDownVotes(int postId);

    Collection<Post> allPostsOrdered();
}
