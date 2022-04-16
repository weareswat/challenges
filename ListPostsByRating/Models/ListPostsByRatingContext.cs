using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ListPostsByRating.Models.ExtensionMethods;

namespace ListPostsByRating.Models
{
    public class ListPostsByRatingContext : DbContext
    {
        public DbSet<BlogPost> BlogPost { get; set; }


        public ListPostsByRatingContext(DbContextOptions<ListPostsByRatingContext> options)
            : base(options) { }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            // Hard coded BlogPost data
            // Upvote Percentage and Vote Ratio are both automatically calculated

            modelBuilder.Entity<BlogPost>().HasData(
            new BlogPost
            {
                Id = 1,
                Upvotes = 600,
                Downvotes = 400,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 2,
                Upvotes = 6,
                Downvotes = 4,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 3,
                Upvotes = 2,
                Downvotes = 8,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 4,
                Upvotes = 200,
                Downvotes = 800,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 5,
                Upvotes = 800,
                Downvotes = 800,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 6,
                Upvotes = 8,
                Downvotes = 8,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 7,
                Upvotes = 967,
                Downvotes = 800,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            new BlogPost
            {
                Id = 8,
                Upvotes = 7,
                Downvotes = 5,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio(),

            /* If you wish to add another BlogPost with your own values
             * don't forget to add a comma after every extension method
             * (Line 108, in this case) and to increment the Id!
             */
            new BlogPost
            {
                Id = 9,
                Upvotes = 0,
                Downvotes = 0,
                Ratio = 0,
                UpvotePercentage = 0
            }.CalculateUpvotePercentage().CalculateVoteRatio()
            );
        }

    }

}
