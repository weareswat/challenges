package com.Joao_Branco.listpostsbyrating.services;

import com.Joao_Branco.listpostsbyrating.Exceptions.NewPostException;
import com.Joao_Branco.listpostsbyrating.domain.Post;
import com.Joao_Branco.listpostsbyrating.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Override
    public Post addPost(int upvotes, int downvotes, String postBody) throws NewPostException {

        Integer postId = postRepository.create(upvotes, downvotes, postBody);

        return postRepository.findByPostId(postId);
    }


    @Override
    public Post findPostById(int postId) {
        return postRepository.findByPostId(postId);
    }

    @Override
    public void incrementUpvotes(int postId) {
        postRepository.incrementUpvotes(postId);
    }

    @Override
    public void incrementDownVotes(int postId) {
        postRepository.incrementDownVotes(postId);
    }

    @Override
    public Collection<Post> allPostsOrdered() {
        return postRepository.allPostsOrdered();
    }
}
