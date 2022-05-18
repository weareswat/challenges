package com.gmail.etpr99.jose.listpostsbyrating.services;

import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import com.gmail.etpr99.jose.listpostsbyrating.repositories.PostRepository;
import com.gmail.etpr99.jose.listpostsbyrating.services.impl.PostServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository postRepository;

    private PostService postService;

    @Before
    public void setUp() {
        postService = new PostServiceImpl(postRepository);
    }

    @Test
    public void testGetAll() {
        Post post1 = new Post(1L, 9L, 1L);
        Post post2 = new Post(2L, 7L, 1L);
        Post post3 = new Post(3L, 8L, 1L);

        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.persist(post3);
        entityManager.flush();

        List<Post> posts = postService.getAll();
        assertThat(posts.get(0).getId()).isEqualTo(post1.getId());
        assertThat(posts.get(1).getId()).isEqualTo(post3.getId());
        assertThat(posts.get(2).getId()).isEqualTo(post2.getId());
    }

    @Test
    public void testGetPost() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.getPost(post.getId());
        assertThat(found.getId()).isEqualTo(post.getId());
    }

    @Test
    public void testUpvotePostByPostObj() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.upvotePost(post);
        assertThat(found.getUpvotes()).isEqualTo(10L);
    }

    @Test
    public void testUpvotePostById() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.upvotePost(post.getId());
        assertThat(found.getUpvotes()).isEqualTo(10L);
    }

    @Test
    public void whenUpvotingNonExistentPost_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.upvotePost(new Post(2L, 9L, 1L)));
        assertThrows(IllegalArgumentException.class, () -> postService.upvotePost(2L));
    }

    @Test
    public void testDownvotePostByPostObj() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.downvotePost(post);
        assertThat(found.getDownvotes()).isEqualTo(2L);
    }

    @Test
    public void testDownvotePostById() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.downvotePost(post.getId());
        assertThat(found.getDownvotes()).isEqualTo(2L);
    }

    @Test
    public void whenDownvotingNonExistentPost_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.downvotePost(new Post(2L, 9L, 1L)));
        assertThrows(IllegalArgumentException.class, () -> postService.downvotePost(2L));
    }

    @Test
    public void testInsertPost() {
        Post post = new Post(1L, 9L, 1L);
        Post inserted = postService.insertPost(post);
        assertThat(inserted.getId()).isEqualTo(post.getId());
    }

    @Test
    public void testInsertPosts() {
        Post post1 = new Post(1L, 9L, 1L);
        Post post2 = new Post(2L, 7L, 1L);
        Post post3 = new Post(3L, 8L, 1L);

        List<Post> posts = postService.insertPosts(Arrays.asList(post1, post2, post3));
        assertThat(posts.get(0).getId()).isEqualTo(post1.getId());
        assertThat(posts.get(1).getId()).isEqualTo(post2.getId());
        assertThat(posts.get(2).getId()).isEqualTo(post3.getId());
    }

    @Test
    public void testUpdatePost() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        Post found = postService.updatePost(post);
        assertThat(found.getId()).isEqualTo(post.getId());
    }

    @Test
    public void whenUpdatingNonExistentPost_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.updatePost(new Post(2L, 9L, 1L)));
    }

    @Test
    public void testUpdatePosts() {
        Post post1 = new Post(1L, 9L, 1L);
        Post post2 = new Post(2L, 7L, 1L);
        Post post3 = new Post(3L, 8L, 1L);

        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.persist(post3);
        entityManager.flush();

        List<Post> posts = postService.updatePosts(Arrays.asList(post1, post2, post3));
        assertThat(posts.get(0).getId()).isEqualTo(post1.getId());
        assertThat(posts.get(1).getId()).isEqualTo(post2.getId());
        assertThat(posts.get(2).getId()).isEqualTo(post3.getId());
    }

    @Test
    public void whenUpdatingNonExistentPosts_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.updatePosts(Arrays.asList(new Post(2L, 9L, 1L), new Post(3L, 7L, 1L))));
    }

    @Test
    public void testDeletePost() {
        Post post = new Post(1L, 9L, 1L);
        entityManager.persist(post);
        entityManager.flush();

        postService.deletePost(post);
        assertThat(postRepository.findById(post.getId())).isEmpty();
    }

    @Test
    public void whenDeletingNonExistentPost_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.deletePost(new Post(2L, 9L, 1L)));
    }

    @Test
    public void testDeletePosts() {
        Post post1 = new Post(1L, 9L, 1L);
        Post post2 = new Post(2L, 7L, 1L);
        Post post3 = new Post(3L, 8L, 1L);

        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.persist(post3);
        entityManager.flush();

        postService.deletePosts(Arrays.asList(post1, post2, post3));
        assertThat(postRepository.findById(post1.getId())).isEmpty();
        assertThat(postRepository.findById(post2.getId())).isEmpty();
        assertThat(postRepository.findById(post3.getId())).isEmpty();
    }

    @Test
    public void whenDeletingNonExistentPosts_thenThrow() {
        assertThrows(IllegalArgumentException.class, () -> postService.deletePosts(Arrays.asList(new Post(2L, 9L, 1L), new Post(3L, 7L, 1L))));
    }
}
