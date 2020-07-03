using Api.Data;
using Api.DTOs;
using Api.Mappers;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Api.Services
{
    public interface IPostService
    {
        Task<PostDto> DownVotePostAsync(int id);
        Task<IEnumerable<PostDto>> GetAllPostsAsync();
        Task<PostDto> UpVotePostAsync(int id);
    }

    public class PostService : IPostService
    {
        private readonly IPostsRepository _postsRepository;

        public PostService(IPostsRepository postsRepository)
        {
            _postsRepository = postsRepository ?? throw new System.ArgumentNullException(nameof(postsRepository));
        }

        public async Task<PostDto> DownVotePostAsync(int id)
        {
            // Find post
            var postFound = await _postsRepository.GetByIdAsync(id)
                .ConfigureAwait(false);
            // Update DownVote
            postFound.DownVotes++;
            var updatedPost = await _postsRepository.UpdateAsync(postFound)
                .ConfigureAwait(false);
            return updatedPost.ToPostDto();
        }

        public async Task<IEnumerable<PostDto>> GetAllPostsAsync()
        {
            var results = await _postsRepository.GetAllAsync()
                .ConfigureAwait(false);
            var orderedPosts = results.OrderByDescending(post => post.Ratio)
                .ThenByDescending(post => post.DiffVotes);
            return orderedPosts.Select(post => post.ToPostDto());
        }

        public async Task<PostDto> UpVotePostAsync(int id)
        {
            // Find post
            var postFound = await _postsRepository.GetByIdAsync(id)
                .ConfigureAwait(false);
            // Update UpVote
            postFound.UpVotes++;
            var updatedPost = await _postsRepository.UpdateAsync(postFound)
                .ConfigureAwait(false);
            return updatedPost.ToPostDto();
        }
    }
}
