using Api.Data;
using Api.DTOs;
using Api.Models;
using Api.Services;
using FluentAssertions;
using Moq;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xunit;

namespace Api.UnitTests.Services
{
    public class PostServiceUnitTests
    {
        [Fact()]
        public async Task GetAllPostsAsync_ContainsPosts_ReturnsAllPosts()
        {
            // Arrange
            var repoStub = new Mock<IPostsRepository>();
            repoStub.Setup(repo => repo.GetAllAsync())
                .ReturnsAsync(new List<Post> { new Post { Id = 1 } });
            var sut = new PostService(repoStub.Object);
            // Act
            var result =  await sut.GetAllPostsAsync();
            // Assert
            result.Should().HaveCount(expected: 1);
        }

        [Fact()]
        public async Task GetAllPostsAsync_DoesNotContainsPosts_ReturnsEmptyCollection()
        {
            // Arrange
            var repoStub = new Mock<IPostsRepository>();
            repoStub.Setup(repo => repo.GetAllAsync())
                .ReturnsAsync(new List<Post>());
            var sut = new PostService(repoStub.Object);
            // Act
            var result = await sut.GetAllPostsAsync();
            // Assert
            result.Should().BeEmpty();
        }

        [Fact()]
        public async Task GetAllPostsAsync_ContainsPostsWithVotes_ReturnsHighestRatingPostsFirst()
        {
            // Arrange
            var repoStub = new Mock<IPostsRepository>();
            repoStub.Setup(repo => repo.GetAllAsync())
                .ReturnsAsync(new List<Post>
                {
                    new Post { Id = 1, DownVotes = 4, UpVotes = 6 },
                    new Post { Id = 2, DownVotes = 400, UpVotes = 600 }
                });
            var sut = new PostService(repoStub.Object);
            var expected = new List<PostDto>
            {
                new PostDto { Id = 2, DownVotes = 400, UpVotes = 600 },
                new PostDto { Id = 1, DownVotes = 4, UpVotes = 6 }
            };
            // Act
            var result = await sut.GetAllPostsAsync();
            // Assert
            result.Should().BeEquivalentTo(expected);
        }
    }
}
