package com.mixfar.posts.service;

import com.mixfar.posts.model.Post;
import com.mixfar.posts.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("postService")
@Transactional
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post findByPostId(Long postId){
        return postRepository.findByPostId(postId);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> findAllByOrderByRatingDesc() {
        return postRepository.findAllByOrderByRatingDesc();
    }

    public void save(Post post) {
        postRepository.save(post);
    }
}
