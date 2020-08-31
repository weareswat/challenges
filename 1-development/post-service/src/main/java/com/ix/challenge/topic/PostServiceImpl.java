package com.ix.challenge.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository repository;

    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();

        repository.findAll(Sort.by(Sort.Direction.DESC, "rating")).forEach(posts::add);

        return posts;
    }

    public Post savePost(Post post) {
        return repository.save(post);
    }

    public Post upVote(int postId) {
        PostResponse response = new PostResponse();
        Optional<Post> result = repository.findById(postId);
        Post post;

        if(result.isPresent()){
            post = result.get();
        } else {
            throw new PostNotFoundException(postId);
        }

        post.setUpVotes(post.getUpVotes() + 1);
        post.setRating(post.getUpVotes() - post.getDownVotes());

        repository.save(post);

        return post;
    }

    public Post downVote(int postId) {
        PostResponse response = new PostResponse();
        Optional<Post> result = repository.findById(postId);
        Post post;

        if(result.isPresent()){
            post = result.get();
        } else {
            throw new PostNotFoundException(postId);
        }

        post.setDownVotes(post.getDownVotes() + 1);
        post.setRating(post.getUpVotes() - post.getDownVotes());

        repository.save(post);

        return post;
    }
}
