package com.ix.challenge.topic;

import com.ix.challenge.PostServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostServiceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
public class PostControllerIT {

    @Autowired
    PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenPosts_whenGetAllPosts_thenStatus200() throws Exception {
        Post createdPost = createTestPost(100,50);

        mockMvc.perform(get("/posts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].upVotes").value(createdPost.getUpVotes()))
                .andExpect(jsonPath("$[0].downVotes").value(createdPost.getDownVotes()));
    }

    private Post createTestPost(int upVotes, int downVotes) {
        Post post = new Post(1, upVotes, downVotes);

        return postService.savePost(post);
    }
}
