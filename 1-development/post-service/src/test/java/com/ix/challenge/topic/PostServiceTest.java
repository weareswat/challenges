package com.ix.challenge.topic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class PostServiceTest {

    @TestConfiguration
    static class PostServiceTestConfiguration{
        @Bean
        public PostService postService() {
            return new PostServiceImpl();
        }
    }

    @MockBean
    private PostRepository postRepository;

    @Autowired
    private PostService postService;


    @Before
    public void setUp() {
        Post post = new Post(5,10,10);

        Mockito.when(postRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"))).thenReturn(getAllMockReturn());
        Mockito.when(postRepository.findById(post.getId())).thenReturn(Optional.of(post));
        Mockito.when(postRepository.save(Mockito.any(Post.class))).thenReturn(post);
    }

    @Test
    public void whenGetAll_thenShouldReturnValidData() {
        int actSize = postService.getAll().size();
        int expSize = 2;
        Assert.assertEquals(expSize, actSize);
    }

    @Test
    public void whenUpVote_thenRatingShouldChange() {
        int id = 5;
        Post response = postService.upVote(id);

        Assert.assertEquals(11, response.getUpVotes());
        Assert.assertEquals(1, response.getRating());
    }

    @Test
    public void whenDownVote_thenRatingShouldChange() {
        int id = 5;
        Post response = postService.downVote(id);

        Assert.assertEquals(11, response.getDownVotes());
        Assert.assertEquals(-1, response.getRating());
    }

    private List<Post> getAllMockReturn() {
        List<Post> result = new ArrayList<>();

        Post post = new Post(1,600,400);
        Post post2 = new Post(2,6,4);

        result.add(post);
        result.add(post2);

        return result;
    }



}
