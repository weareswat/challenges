package com.mixfar.posts;

import com.mixfar.posts.model.Post;
import com.mixfar.posts.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.Date;

@SpringBootApplication
public class PostsApplication implements CommandLineRunner {

    @Autowired
    PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(PostsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        postRepository.save(new Post(1L, "First Post", "First Post inserted on the database",
                600L, 400L, LocalDate.of(2020, 3, 2)));
        postRepository.save(new Post(2L, "Second Post", "First Post inserted on the database",
                6L, 4L, LocalDate.of(2021, 1, 2)));
        postRepository.save(new Post(3L, "Third Post", "First Post inserted on the database",
                50L, 50L, LocalDate.of(2020, 7, 23)));
        postRepository.save(new Post(4L, "Four Post", "First Post inserted on the database",
                40L, 60L, LocalDate.of(2020, 9, 15)));
        postRepository.save(new Post(5L, "Five Post", "First Post inserted on the database",
                1600L, 40L, LocalDate.of(2020, 12, 25)));
        postRepository.save(new Post(6L, "Six Post", "First Post inserted on the database",
                2L, 0L, LocalDate.of(2021, 1, 15)));
    }

}
