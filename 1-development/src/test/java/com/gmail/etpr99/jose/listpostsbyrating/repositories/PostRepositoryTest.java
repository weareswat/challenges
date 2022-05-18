package com.gmail.etpr99.jose.listpostsbyrating.repositories;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void testGetPostUpvotes() {
        Post post = new Post(1L, "Lorem ipsum ipsum ipsum ipsum ipsum", 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Long upvotes = postRepository.getPostUpvotes(post.getId());
        assertThat(upvotes).isEqualTo(post.getUpvotes());
    }

    @Test
    public void testGetNonExistentPostUpvotes() {
        assertThat(postRepository.getPostUpvotes(1L)).isNull();
    }

    @Test
    public void testGetPostDownvotes() {
        Post post = new Post(1L, "Lorem ipsum ipsum ipsum ipsum ipsum", 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Long downvotes = postRepository.getPostDownvotes(post.getId());
        assertThat(downvotes).isEqualTo(post.getDownvotes());
    }

    @Test
    public void testGetNonExistentPostDownvotes() {
        assertThat(postRepository.getPostDownvotes(1L)).isNull();
    }
}
