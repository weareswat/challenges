package pt.rubenmarques.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;
import pt.rubenmarques.domain.dtos.post.PostDTO;
import pt.rubenmarques.domain.post.Post;
import pt.rubenmarques.exception.PostNotFoundException;
import pt.rubenmarques.repository.post.PostRepository;
import pt.rubenmarques.service.post.PostService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    @InjectMocks
    private PostService service;

    @Mock
    private PostRepository repository;

    @Test
    public void whenGetPostsOrderedByRatingDESCThenAssertExpected() {
        final var longNumberMock = 1L;
        final var stringMock = "A";
        final var lowerVoteCountMock = 1L;
        final var higherVoteCountMock = 2L;

        final var post1Mock = Post.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(higherVoteCountMock)
                .downVotes(longNumberMock)
                .build();
        final var post1DTOMock = PostDTO.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(higherVoteCountMock)
                .downVotes(longNumberMock)
                .build();
        final var post2Mock = Post.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(lowerVoteCountMock)
                .downVotes(longNumberMock)
                .build();
        final var post2DTOMock = PostDTO.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(lowerVoteCountMock)
                .downVotes(longNumberMock)
                .build();
        final var postListMock = List.of(post1Mock, post2Mock);

        when(this.repository.findAll()).thenReturn(postListMock);
        final var result = this.service.getPostsOrderedByRatingDESC();

        assertFalse(CollectionUtils.isEmpty(result));
        assertEquals(result.get(0), post1DTOMock);
        assertEquals(result.get(1), post2DTOMock);
    }

    @Test(expected = PostNotFoundException.class)
    public void givenPostIdWhenUpVotePostByIdThenThrowException() throws PostNotFoundException {
        final var longNumberMock = 1L;

        when(this.repository.findById(longNumberMock)).thenReturn(Optional.empty());
        this.service.upVotePostById(longNumberMock);
    }

    @Test
    public void givenPostIdWhenUpVotePostByIdThenAssertExpected() {
        final var longNumberMock = 1L;
        final var stringMock = "A";

        final var post1Mock = Post.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(longNumberMock)
                .downVotes(longNumberMock)
                .build();

        when(this.repository.findById(longNumberMock)).thenReturn(Optional.of(post1Mock));
        when(repository.save(Mockito.any(Post.class))).thenAnswer(i -> i.getArguments()[0]);
        final var result = this.service.upVotePostById(longNumberMock);

        assertTrue(Objects.nonNull(result));
        assertEquals(result.getId(), post1Mock.getId());
        assertEquals(result.getUpVotes().longValue(), 2L);
    }

    @Test(expected = PostNotFoundException.class)
    public void givenPostIdWhenDownVotePostByIdThenThrowException() throws PostNotFoundException {
        final var longNumberMock = 1L;

        when(this.repository.findById(longNumberMock)).thenReturn(Optional.empty());
        this.service.downVotePostById(longNumberMock);
    }

    @Test
    public void givenPostIdWhenDownVotePostByIdThenAssertExpected() {
        final var longNumberMock = 1L;
        final var stringMock = "A";

        final var post1Mock = Post.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(longNumberMock)
                .downVotes(longNumberMock)
                .build();

        when(this.repository.findById(longNumberMock)).thenReturn(Optional.of(post1Mock));
        when(repository.save(Mockito.any(Post.class))).thenAnswer(i -> i.getArguments()[0]);
        final var result = this.service.downVotePostById(longNumberMock);

        assertTrue(Objects.nonNull(result));
        assertEquals(result.getId(), post1Mock.getId());
        assertEquals(result.getDownVotes().longValue(), 0L);
    }

    @Test
    public void givenPostIdWith0DownVotesWhenDownVotePostByIdThenAssertExpected() {
        final var longNumberMock = 1L;
        final var stringMock = "A";
        final var downVotesMock = 0L;

        final var post1Mock = Post.builder()
                .id(longNumberMock)
                .title(stringMock)
                .slug(stringMock)
                .content(stringMock)
                .upVotes(longNumberMock)
                .downVotes(downVotesMock)
                .build();

        when(this.repository.findById(longNumberMock)).thenReturn(Optional.of(post1Mock));
        when(repository.save(Mockito.any(Post.class))).thenAnswer(i -> i.getArguments()[0]);
        final var result = this.service.downVotePostById(longNumberMock);

        assertTrue(Objects.nonNull(result));
        assertEquals(result.getId(), post1Mock.getId());
        assertEquals(result.getDownVotes().longValue(), 0L);
    }

}
