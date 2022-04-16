using ListPostsByRating.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ListPostsByRating.Models.ExtensionMethods;

namespace ListPostsByRating.Controllers
{
    [ApiController]
    public class BlogPostController : ControllerBase
    {
        private readonly ListPostsByRatingContext blogPostContext;

        public BlogPostController(ListPostsByRatingContext blogPostContext)
        {
            if (blogPostContext == null)
                throw new ArgumentNullException();

            this.blogPostContext = blogPostContext;
            this.blogPostContext.Database.EnsureCreated();
        }

        [HttpGet("posts")]
        public async Task<IActionResult> FetchAllBlogPosts()
        {
            return Ok(await blogPostContext.BlogPost.OrderByDescending(i => i.Ratio)
                                                    .ThenByDescending(i => i.Upvotes)
                                                    .ThenByDescending(i => i.UpvotePercentage)
                                                    .ToListAsync());
        }

        [HttpPut("upvote/{post_id:int}")]
        public async Task<IActionResult> UpvotePost(int post_id)
        {
            if (post_id <= 0)
                return Conflict();

            BlogPost blogPost = blogPostContext.BlogPost.Where(b => b.Id == post_id).FirstOrDefault();

            if (blogPost == null)
                return NotFound();

            try
            {
                blogPost.Upvotes++;
                blogPost.CalculateUpvotePercentage().CalculateVoteRatio();

                blogPostContext.Entry(blogPost).State = EntityState.Modified;

                await blogPostContext.SaveChangesAsync();
            }
            catch(ArgumentNullException)
            {
                return NotFound();
            }
            catch (DbUpdateConcurrencyException)
            {
                throw;
            }


            return Ok(blogPost);
        }

        [HttpPut("downvote/{post_id:int}")]
        public async Task<IActionResult> DownvotePost(int post_id)
        {
            if (post_id <= 0)
                return Conflict();

            BlogPost blogPost = blogPostContext.BlogPost.Where(b => b.Id == post_id).FirstOrDefault();

            if (blogPost == null)
                return NotFound();

            try
            {
                blogPost.Downvotes++;
                blogPost.CalculateUpvotePercentage().CalculateVoteRatio();

                blogPostContext.Entry(blogPost).State = EntityState.Modified;

                await blogPostContext.SaveChangesAsync();
            }
            catch (ArgumentNullException)
            {
                return NotFound();
            }
            catch (DbUpdateConcurrencyException)
            {
                throw;
            }


            return Ok(blogPost);
        }
    }
}
