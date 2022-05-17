package com.gmail.etpr99.jose.listpostsbyrating.repositories;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    @Test
    public void whenFindById_thenReturnPost() {
        // given
        Post post = new Post(1L);
        entityManager.persist(post);
        entityManager.flush();

        // when
        Post found = postRepository.findById(post.getId()).get();

        // then
        assertThat(found.getId()).isEqualTo(post.getId());
    }

    // Test the insertion of a post with upvotes and downvotes.
    // The upvote and downvote percentages should be calculated correctly.
    @Test
    public void whenFindByUpvotes_thenReturnPost() {
        // given
        Post post = new Post(1L, 2L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        // when
        List<Post> posts = postRepository.findByUpvotes(post.getUpvotes());

        // then
        assertThat(posts).isNotEmpty();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getId()).isEqualTo(post.getId());
        assertThat(posts.get(0).getUpvotePercentage()).isEqualTo(66);
        assertThat(posts.get(0).getDownvotePercentage()).isEqualTo(33);
    }

    // Test the insertion of a post with upvotes and downvotes.
    // The upvote and downvote percentages should be calculated correctly.
    @Test
    public void whenFindByDownvotes_thenReturnPost() {
        // given
        Post post = new Post(1L, 2L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        // when
        List<Post> posts = postRepository.findByDownvotes(post.getDownvotes());

        // then
        assertThat(posts).isNotEmpty();
        assertThat(posts).hasSize(1);
        assertThat(posts.get(0).getId()).isEqualTo(post.getId());
        assertThat(posts.get(0).getUpvotePercentage()).isEqualTo(66);
        assertThat(posts.get(0).getDownvotePercentage()).isEqualTo(33);
    }
}
