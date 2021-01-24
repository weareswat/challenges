package com.mixfar.posts;

import com.mixfar.posts.controllers.PostController;
import com.mixfar.posts.model.Post;
import com.mixfar.posts.repositories.PostRepository;
import com.mixfar.posts.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostRestControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    /**
     * Create post with success
     * @throws Exception
     */
    @Test
    public void create_OK() throws Exception {

        String post_json = "{\"postTitle\": \"Post created now\",\"postBody\": \"Another Post body\",\"upVotes\": 0,\"downVotes\": 0,\"rating\": 0.0,\"publishDate\": \"2021-01-24\"}";

        mvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON).content(post_json))
                .andExpect(status().isCreated());
    }

    /**
     * Get Posts By Rating Desc with Success
     * @throws Exception
     */
    @Test
    public void getPostsByRatingDesc_OK() throws Exception {
        mvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    /**
     * Upvote with success
     * @throws Exception
     */
    @Test
    public void upvote_OK() throws Exception {
        Post post = new Post(1L,"Title", "Body", 0L, 0L, LocalDate.now());

        given(postService.findByPostId(1L)).willReturn(post);

        mvc.perform(put("/upvote/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.postId").value(post.getPostId()))
                .andExpect(jsonPath("data.upVotes").value(1L));
    }

    /**
     * Downvote with success
     * @throws Exception
     */
    @Test
    public void downvote_OK() throws Exception {
        Post post = new Post(1L,"Title", "Body", 0L, 0L, LocalDate.of(2021, 1, 24));

        given(postService.findByPostId(1L)).willReturn(post);

        mvc.perform(put("/downvote/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("data.postId").value(post.getPostId()))
                .andExpect(jsonPath("data.downVotes").value(1L));
    }

    /**
     * Create post with errors
     * @throws Exception
     */
    @Test
    public void create_NOK() throws Exception {
        mvc.perform(post("/create")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    /**
     * Upvote with errors
     * @throws Exception
     */
    @Test
    public void upvote_NOK() throws Exception {
        Post post = new Post(1L,"Title", "Body", 0L, 0L, LocalDate.now());

        given(postService.findByPostId(1L)).willReturn(post);

        mvc.perform(put("/upvote/{id}", 12L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code").value(404))
                .andExpect(jsonPath("error[0].field").value("id"))
                .andExpect(jsonPath("error[0].errorMessage").value("Post with id: 12 not found"));
    }

    /**
     * Downvote with errors
     * @throws Exception
     */
    @Test
    public void downvote_NOK() throws Exception {
        Post post = new Post(1L,"Title", "Body", 0L, 0L, LocalDate.of(2021, 1, 24));

        given(postService.findByPostId(1L)).willReturn(post);

        mvc.perform(put("/downvote/{id}", 13L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("code").value(404))
                .andExpect(jsonPath("error[0].field").value("id"))
                .andExpect(jsonPath("error[0].errorMessage").value("Post with id: 13 not found"));
    }
}
