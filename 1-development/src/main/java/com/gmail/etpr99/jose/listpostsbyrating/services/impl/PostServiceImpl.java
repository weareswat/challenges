package com.gmail.etpr99.jose.listpostsbyrating.services.impl;

import com.gmail.etpr99.jose.listpostsbyrating.exceptions.PostNotFoundException;
import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import com.gmail.etpr99.jose.listpostsbyrating.repositories.PostRepository;
import com.gmail.etpr99.jose.listpostsbyrating.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    /**
     * The repository for {@link Post} objects.
     */
    private final PostRepository postRepository;

    /**
     * Creates a new instance of {@link PostServiceImpl}.
     *
     * @param postRepository The repository for {@link Post} objects.
     */
    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> getAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "upvotes"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getPostUpvotes(Long id) {
        Long upvotes = postRepository.getPostUpvotes(id);
        if (upvotes == null) {
            throw new PostNotFoundException("Post with ID " + id + " does not exist.");
        }

        return upvotes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upvotePost(Post post) {
        if (!postRepository.existsById(post.getId())) {
            throw new PostNotFoundException("Post with ID " + post.getId() + " does not exist.");
        }

        post.setUpvotes(post.getUpvotes() + 1);
        postRepository.save(post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void upvotePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setUpvotes(post.getUpvotes() + 1);
            postRepository.save(post);
            return;
        }

        throw new PostNotFoundException("Post with ID " + id + " does not exist.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getPostDownvotes(Long id) {
        return postRepository.getPostDownvotes(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void downvotePost(Post post) {
        if (!postRepository.existsById(post.getId())) {
            throw new PostNotFoundException("Post with ID " + post.getId() + " does not exist.");
        }

        post.setDownvotes(post.getDownvotes() + 1);
        postRepository.save(post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void downvotePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setDownvotes(post.getDownvotes() + 1);
            postRepository.save(post);
            return;
        }

        throw new PostNotFoundException("Post with ID " + id + " does not exist.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post insertPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Post> insertPosts(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Post updatePost(Post post) {
        if (postRepository.existsById(post.getId())) {
            return postRepository.save(post);
        } else {
            throw new PostNotFoundException("Post does not exist.");
        }
    }

    @Override
    public List<Post> updatePosts(List<Post> posts) {
        if (posts.stream().allMatch(post -> postRepository.existsById(post.getId()))) {
            return postRepository.saveAll(posts);
        } else {
            throw new PostNotFoundException("One of the given posts does not exist.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePost(Post post) {
        if (postRepository.existsById(post.getId())) {
            postRepository.delete(post);
        } else {
            throw new PostNotFoundException("Post does not exist.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deletePosts(List<Post> posts) {
        if (posts.stream().allMatch(post -> postRepository.existsById(post.getId()))) {
            postRepository.deleteAll(posts);
        } else {
            throw new PostNotFoundException("One of the given posts does not exist.");
        }
    }
}
