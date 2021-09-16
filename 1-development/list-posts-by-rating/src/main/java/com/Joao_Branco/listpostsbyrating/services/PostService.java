package com.Joao_Branco.listpostsbyrating.services;

import com.Joao_Branco.listpostsbyrating.Exceptions.NewPostException;
import com.Joao_Branco.listpostsbyrating.domain.Post;

import java.util.Collection;

public interface PostService {

    Post addPost(int upvotes, int downvotes, String postBody) throws NewPostException;

    Post findPostById(int postId);

    void incrementUpvotes(int postId);

    void incrementDownVotes(int postId);

    Collection<Post> allPostsOrdered();
}
