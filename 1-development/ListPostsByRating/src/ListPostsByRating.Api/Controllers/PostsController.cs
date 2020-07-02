using Api.DTOs;
using Api.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace Api.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly IPostService _postService;

        public PostsController(IPostService postService)
        {
            _postService = postService ?? throw new System.ArgumentNullException(nameof(postService));
        }

        [HttpGet]
        public async Task<IEnumerable<PostDto>> Get()
        {
            return await _postService.GetAllPostsAsync();
        }

        [HttpPut("upvote/{postId}")]
        public async Task<PostDto> Upvote(int id)
        {
            return await _postService.UpVotePostAsync(id);
        }

        [HttpPut("downvote/{postId}")]
        public async Task<PostDto> Downvote(int id)
        {
            return await _postService.UpVotePostAsync(id);
        }
    }
}
