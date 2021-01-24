package com.mixfar.posts.repositories;

import com.mixfar.posts.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByPostId(Long postId);
    List<Post> findAll();
    List<Post> findAllByOrderByRatingDesc();
}
