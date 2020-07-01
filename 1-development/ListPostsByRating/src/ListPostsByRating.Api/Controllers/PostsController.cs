using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;

namespace Api.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class PostsController : ControllerBase
    {
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        [HttpPut("upvote/{postId}")]
        public void Upvote(int id, [FromBody] string value)
        {
        }

        [HttpPut("downvote/{postId}")]
        public void Downvote(int id, [FromBody] string value)
        {
        }
    }
}
