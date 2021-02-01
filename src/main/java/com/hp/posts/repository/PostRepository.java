package com.hp.posts.repository;

import com.hp.posts.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostModel, Integer> {
    List<PostModel> findAll();
}
