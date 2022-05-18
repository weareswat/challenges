package com.gmail.etpr99.jose.listpostsbyrating.repositories;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The post repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * Gets the upvotes of the post with the given ID.
     */
    @Query("SELECT p.upvotes FROM Post p WHERE p.id = :post_id")
    Long getPostUpvotes(@Param("post_id") Long id);

    /**
     * Gets the downvotes of the post with the given ID.
     */
    @Query("SELECT p.downvotes FROM Post p WHERE p.id = :post_id")
    Long getPostDownvotes(@Param("post_id") Long id);
}
