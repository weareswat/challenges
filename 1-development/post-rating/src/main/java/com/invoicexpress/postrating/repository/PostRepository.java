package com.invoicexpress.postrating.repository;

import com.invoicexpress.postrating.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
}
