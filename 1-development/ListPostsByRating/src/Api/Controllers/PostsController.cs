using Api.Exceptions;
using Api.Services;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace Api.Controllers
{
    [ApiController]
    public class PostsController : ControllerBase
    {
        private readonly IPostService _postService;

        public PostsController(IPostService postService)
        {
            _postService = postService ?? throw new System.ArgumentNullException(nameof(postService));
        }

        [HttpGet("posts")]
        public async Task<IActionResult> Get()
        {
            var result = await _postService.GetAllPostsAsync();
            return base.Ok(result);
        }

        [HttpPut("upvote/{postId}")]
        public async Task<IActionResult> Upvote(int postId)
        {
            try
            {
                var result = await _postService.UpVotePostAsync(postId);
                return base.Ok(result);
            }
            catch (PostNotFoundException)
            {

                return base.NotFound();
            }
        }

        [HttpPut("downvote/{postId}")]
        public async Task<IActionResult> Downvote(int postId)
        {
            try
            {
                var result = await _postService.DownVotePostAsync(postId);
                return base.Ok(result);
            }
            catch (PostNotFoundException)
            {
                return base.NotFound();
            }
        }
    }
}
