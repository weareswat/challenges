package com.gmail.etpr99.jose.listpostsbyrating.repositories;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The post repository.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
