package com.gmail.etpr99.jose.listpostsbyrating.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.etpr99.jose.listpostsbyrating.models.Post;
import com.gmail.etpr99.jose.listpostsbyrating.services.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    public void testGetAll() throws Exception {
        Post post1 = new Post(1L, "Lorem ipsum", 9L, 1L);
        Post post2 = new Post(2L, "Lorem ipsum lorem", 4L, 3L);
        Post post3 = new Post(3L, "Lorem ipsum lorem ipsum", 2L, 2L);

        given(postService.getAll()).willReturn(List.of(post1, post2, post3));

        mvc.perform(MockMvcRequestBuilders.get("/posts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(post1.getId()))
            .andExpect(jsonPath("$[0].text").value(post1.getText()))
            .andExpect(jsonPath("$[0].upvotes").value(post1.getUpvotes()))
            .andExpect(jsonPath("$[0].downvotes").value(post1.getDownvotes()))
            .andExpect(jsonPath("$[0].upvotePercentage").value(post1.getUpvotePercentage()))
            .andExpect(jsonPath("$[0].downvotePercentage").value(post1.getDownvotePercentage()))
            .andExpect(jsonPath("$[1].id").value(post2.getId()))
            .andExpect(jsonPath("$[1].text").value(post2.getText()))
            .andExpect(jsonPath("$[1].upvotes").value(post2.getUpvotes()))
            .andExpect(jsonPath("$[1].downvotes").value(post2.getDownvotes()))
            .andExpect(jsonPath("$[1].upvotePercentage").value(post2.getUpvotePercentage()))
            .andExpect(jsonPath("$[1].downvotePercentage").value(post2.getDownvotePercentage()))
            .andExpect(jsonPath("$[2].id").value(post3.getId()))
            .andExpect(jsonPath("$[2].text").value(post3.getText()))
            .andExpect(jsonPath("$[2].upvotes").value(post3.getUpvotes()))
            .andExpect(jsonPath("$[2].downvotes").value(post3.getDownvotes()))
            .andExpect(jsonPath("$[2].upvotePercentage").value(post3.getUpvotePercentage()))
            .andExpect(jsonPath("$[2].downvotePercentage").value(post3.getDownvotePercentage()));
    }

    @Test
    public void testGetAllEmpty() throws Exception {
        given(postService.getAll()).willReturn(List.of());

        mvc.perform(MockMvcRequestBuilders.get("/posts")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testInsertPost() throws Exception {
        Post post = new Post(1L, "Lorem ipsum ipsum ipsum ipsum ipsum", 9L, 1L);

        when(postService.insertPost(any())).thenAnswer(invocation -> {
            if (invocation.getArgument(0, Post.class).getId() == 1L) {
                return post;
            }

            return null;
        });

        mvc.perform(MockMvcRequestBuilders.post("/posts")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(post)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(post.getId()))
            .andExpect(jsonPath("$.text").value(post.getText()))
            .andExpect(jsonPath("$.upvotes").value(post.getUpvotes()))
            .andExpect(jsonPath("$.downvotes").value(post.getDownvotes()))
            .andExpect(jsonPath("$.upvotePercentage").value(post.getUpvotePercentage()))
            .andExpect(jsonPath("$.downvotePercentage").value(post.getDownvotePercentage()));
    }
}
