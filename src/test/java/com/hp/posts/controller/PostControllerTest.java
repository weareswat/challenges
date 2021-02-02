package com.hp.posts.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void getAll() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/posts"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Sql(scripts = {"/test-data.sql"})
    void increaseVote() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/upvote/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    @Sql(scripts = {"/test-data.sql"})
    void decreaseVote() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/downvote/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        Assert.assertEquals("application/json", mvcResult.getResponse().getContentType());
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
