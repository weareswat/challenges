package com.gmail.etpr99.jose.listpostsbyrating.repositories;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The post repository.
 */
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Finds a list of posts by their upvotes.
     */
    List<Post> findByUpvotes(Long upvotes);

    /**
     * Finds a list of posts by their downvotes.
     */
    List<Post> findByDownvotes(Long downvotes);
}
