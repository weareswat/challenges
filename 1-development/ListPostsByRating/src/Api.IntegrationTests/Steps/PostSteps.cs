using Api.Controllers;
using Api.Data;
using Api.DTOs;
using Api.Models;
using Api.Services;
using FluentAssertions;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using TechTalk.SpecFlow;

namespace Api.IntegrationTests.Steps
{
    [Binding]
    public class PostSteps
    {
        private readonly ScenarioContext _context;

        public PostSteps(ScenarioContext context)
        {
            _context = context ?? throw new System.ArgumentNullException(nameof(context));
        }

        [Given(@"there is a set of posts")]
        public void GivenThereIsASetOfPosts()
        {
            _context.Add(key: "posts", value: new List<Post>());
        }

        [Given(@"I have a post with id (.*)")]
        public void GivenIHaveAPostWithId(int postId)
        {
            _context.Set(key: "postId", data: postId);
            var posts = _context.Get<List<Post>>(key: "posts");
            posts.Add(new Post { Id = postId });
        }

        [Given(@"it contains (.*) up votes")]
        public void GivenItContainsUpVotes(int upVotesCount)
        {
            var posts = _context.Get<List<Post>>(key: "posts");
            var postId = _context.Get<int>(key: "postId");
            var index = posts.FindIndex(post => post.Id == postId);
            posts[index].UpVotes = upVotesCount;
        }

        [Given(@"it contains (.*) down votes")]
        public void GivenItContainsDownVotes(int downVotesCount)
        {
            var posts = _context.Get<List<Post>>(key: "posts");
            var postId = _context.Get<int>(key: "postId");
            var index = posts.FindIndex(post => post.Id == postId);
            posts[index].DownVotes = downVotesCount;
        }

        private PostsController InitializeController()
        {
            var repo = new PostsRepository();
            var posts = _context.Get<List<Post>>(key: "posts");
            repo.Seed(posts);
            var service = new PostService(repo);
            return new PostsController(service);
        }

        [When(@"I up vote the post")]
        public async Task WhenIUpVoteThePost()
        {
            var controller = InitializeController();
            var postId = _context.Get<int>(key: "postId");
            var response = await controller.Upvote(postId);
            _context.Add(key: "response", value: response);
        }

        [When(@"I down vote the post")]
        public async Task WhenIDownVoteThePost()
        {
            var controller = InitializeController();
            var postId = _context.Get<int>(key: "postId");
            var response = await controller.Downvote(postId);
            _context.Add(key: "response", value: response);
        }

        [Then(@"the post should have (.*) up vote")]
        public void ThenThePostShouldHaveUpVote(int expectedUpVotesCount)
        {
            var response = (PostDto)_context.Get<OkObjectResult>(key: "response").Value;
            response.UpVotes.Should().Be(expectedUpVotesCount);
        }

        [Then(@"the post should have (.*) down votes")]
        public void ThenThePostShouldHaveDownVotes(int expectedDownVotesCount)
        {
            var response = (PostDto)_context.Get<OkObjectResult>(key: "response").Value;
            response.DownVotes.Should().Be(expectedDownVotesCount);
        }

        [When(@"I get a list of all posts")]
        public async Task WhenIGetAListOfAllPosts()
        {
            var controller = InitializeController();
            var response = await controller.Get();
            _context.Add(key: "response", value: response);
        }

        [Then(@"the first post id should be (.*)")]
        public void ThenTheFirstPostIdShouldBe(int expectedFirstPostId)
        {
            var response = (IEnumerable<PostDto>)_context.Get<OkObjectResult>(key: "response").Value;
            response.First().Id.Should().Be(expectedFirstPostId);
        }

        [Then(@"the second post id should be (.*)")]
        public void ThenTheSecondPostIdShouldBe(int secondPostId)
        {
            var response = (IEnumerable<PostDto>)_context.Get<OkObjectResult>(key: "response").Value;
            response.Last().Id.Should().Be(secondPostId);
        }
    }
}
