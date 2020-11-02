package com.invoicexpress.postrating.service;

import com.invoicexpress.postrating.Enum.VoteEnum;
import com.invoicexpress.postrating.PostRatingApplication;
import com.invoicexpress.postrating.entity.Post;
import com.invoicexpress.postrating.exceptions.InvalidPostIdException;
import com.invoicexpress.postrating.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = PostRatingApplication.class
)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PostServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    public void updateDownVotes() throws InvalidPostIdException {

        Post post = new Post();
        postRepository.save(post);
        post.setDownVotes(1);

        Post postExpectedAfterUpdate = postService.updatePostVotes(post.getId(), VoteEnum.DOWN_VOTE);

        assertThat(post.getDownVotes() == postExpectedAfterUpdate.getDownVotes());

        postRepository.delete(post);

    }

    @Test
    public void updateUpVotes() throws InvalidPostIdException {

        Post post = new Post();
        postRepository.save(post);
        post.setUpVotes(1);

        Post postExpectedAfterUpdate = postService.updatePostVotes(post.getId(), VoteEnum.UP_VOTE);

        assertThat(post.getUpVotes() == postExpectedAfterUpdate.getUpVotes());

        postRepository.delete(post);

    }

    @Test
    public void getAllPosts() {
        List<Post> listOfPosts = new ArrayList<>();
        Post post = new Post();
        Post post2 = new Post();
        Post post3 = new Post();
        listOfPosts.add(post);
        listOfPosts.add(post2);
        listOfPosts.add(post3);
        postRepository.saveAll(listOfPosts);

        List<Post> expectedPost = new ArrayList<>();
        expectedPost.add(postRepository.findById(post.getId()).orElse(null));
        expectedPost.add(postRepository.findById(post2.getId()).orElse(null));
        expectedPost.add(postRepository.findById(post3.getId()).orElse(null));

        assertThat(listOfPosts.equals(expectedPost));

        postRepository.deleteAll(listOfPosts);

    }

    @Test
    public void postWithSameRatioMustHaveDifferentScores(){
        Post post = new Post(10, 1, 0);
        Post postMidValue = new Post(10, 10, 1);
        Post postHigherValue = new Post(10, 100, 10);
        Post postMostValue = new Post(10, 100000, 10000);

        assertThat(postService.calculateScoreOfPost(post) < postService.calculateScoreOfPost(postMidValue));
        assertThat(postService.calculateScoreOfPost(postMidValue) < postService.calculateScoreOfPost(postHigherValue));
        assertThat(postService.calculateScoreOfPost(postHigherValue) < postService.calculateScoreOfPost(postMostValue));
        assertThat(postService.calculateScoreOfPost(postMostValue) > postService.calculateScoreOfPost(post));

    }

    @Test
    public void checkIfTheListIsSorted(){
        List<Post> listOfPosts = new ArrayList<>();
        Post post = new Post();
        post.setUpVotes(10);
        post.setDownVotes(1);
        Post post2 = new Post();
        post2.setUpVotes(100);
        post2.setDownVotes(10);
        Post post3 = new Post();
        post3.setUpVotes(1000);
        post3.setDownVotes(100);
        listOfPosts.add(post);
        listOfPosts.add(post2);
        listOfPosts.add(post3);
        postRepository.saveAll(listOfPosts);

        List<ScorePostDTO> list = postService.getAllPosts();
        for (int i =1; i < list.size(); i++){
            assertThat(list.get(i).getScore() < list.get(i-1).getScore());
        }

        postRepository.deleteAll(listOfPosts);
    }

}
