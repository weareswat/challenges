package com.invoicexpress.postrating.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.invoicexpress.postrating.Enum.PostEnum;
import com.invoicexpress.postrating.Enum.VoteEnum;
import com.invoicexpress.postrating.entity.Post;
import com.invoicexpress.postrating.exceptions.InvalidPostIdException;
import com.invoicexpress.postrating.service.PostService;
import com.invoicexpress.postrating.service.ScorePostDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(value = PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;



    @Test
    public void postDownVoteInvalidPostIdFail() throws InvalidPostIdException, Exception {

        Mockito.when(postService.updatePostVotes(99999, VoteEnum.DOWN_VOTE)).thenThrow(new InvalidPostIdException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/downvote/?post_id=99999").accept(MediaType.APPLICATION_JSON);

        ResponsePostVoteVO expected = new ResponsePostVoteVO();
        expected.setMessage(PostEnum.INVALID_POST_ID.getShdes());
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }

    @Test
    public void postDownVotePostIdSuccess() throws InvalidPostIdException, Exception {
        ResponsePostVoteVO responseMock = new ResponsePostVoteVO();
        Post post = new Post();
        post.setId(1000);
        post.setDownVotes(1);
        post.setUpVotes(0);
        responseMock.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());
        responseMock.setPost(post);

        Mockito.when(postService.updatePostVotes(Mockito.anyInt(), Mockito.any())).thenReturn(post);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/downvote/?post_id=99999").accept(MediaType.APPLICATION_JSON);

        ResponsePostVoteVO expected = new ResponsePostVoteVO();
        expected.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());
        expected.setPost(new Post(1000, 0,1));
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }

    @Test
    public void postUpVoteInvalidPostIdFail() throws InvalidPostIdException, Exception {

        Mockito.when(postService.updatePostVotes(99999, VoteEnum.UP_VOTE)).thenThrow(new InvalidPostIdException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/upvote/?post_id=99999").accept(MediaType.APPLICATION_JSON);

        ResponsePostVoteVO expected = new ResponsePostVoteVO();
        expected.setMessage(PostEnum.INVALID_POST_ID.getShdes());
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }

    @Test
    public void postUpVotePostIdSuccess() throws InvalidPostIdException, Exception {
        ResponsePostVoteVO responseMock = new ResponsePostVoteVO();
        Post post = new Post();
        post.setId(1000);
        post.setDownVotes(1);
        post.setUpVotes(0);
        responseMock.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());
        responseMock.setPost(post);

        Mockito.when(postService.updatePostVotes(Mockito.anyInt(), Mockito.any())).thenReturn(post);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/upvote/?post_id=99999").accept(MediaType.APPLICATION_JSON);

        ResponsePostVoteVO expected = new ResponsePostVoteVO();
        expected.setMessage(PostEnum.UPDATED_POST_VOTE.getShdes());
        expected.setPost(new Post(1000, 0,1));
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }

    @Test
    public void getAllPostsReturnEmptyList() throws Exception {
        List<ScorePostDTO> list = new ArrayList<>();
        ResponsePostVO responsePostVO = new ResponsePostVO();
        Mockito.when(postService.getAllPosts()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts/").accept(MediaType.APPLICATION_JSON);

        ResponsePostVO expected = new ResponsePostVO();
        expected.setMessage(PostEnum.SEARCH_SUCCESSFULLY_CONDUCTED.getShdes());
        List<ScorePostDTO> expectedList = new ArrayList<>();
        expected.setListOfPosts(expectedList);
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }

    @Test
    public void getAllPostsReturnList() throws Exception {
        List<ScorePostDTO> list = new ArrayList<>();
        list.add(new ScorePostDTO(new Post(1000, 10, 1), 2));
        list.add(new ScorePostDTO(new Post(1001, 1, 10), 0.5));
        Mockito.when(postService.getAllPosts()).thenReturn(list);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/posts/").accept(MediaType.APPLICATION_JSON);

        ResponsePostVO expected = new ResponsePostVO();
        expected.setMessage(PostEnum.SEARCH_SUCCESSFULLY_CONDUCTED.getShdes());
        List<ScorePostDTO> expectedList = new ArrayList<>();
        expectedList.add(new ScorePostDTO(new Post(1000, 10, 1), 2));
        expectedList.add(new ScorePostDTO(new Post(1001, 1, 10), 0.5));
        expected.setListOfPosts(expectedList);
        String expectationJson = this.mapToJson(expected);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        String output = response.getContentAsString();

        JSONAssert.assertEquals(expectationJson, output, false);
    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
