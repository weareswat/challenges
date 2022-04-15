using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ListPostsByRating.Models.ExtensionMethods
{

    // <summary>
    // Class full of extension methods used in "Models/ListPostsByRatingContext"
    // for the sole purpose of automatically calculating Upvote Percentage and
    // Vote Ratio, both properties used to rank how high and low a post is.
    // </summary> 
    public static class PostAutoCalculations
    {
        // <summary>
        // Extension method to calculate Upvote Percentage automatically with the given
        // Upvote and Downvote count
        // <returns>
        // BlogPost with the calculated Upvote Percentage
        // </returns>
        // </summary>
        public static BlogPost CalculateUpvotePercentage(this BlogPost blogPost)
        {
            double totalVotes = blogPost.Upvotes + blogPost.Downvotes;
            double upvotePercentage = blogPost.Upvotes / totalVotes;
            upvotePercentage *= 100;

            // Protection against zero divided by zero
            if (Double.IsNaN(upvotePercentage))
                upvotePercentage = 0;

            blogPost.UpvotePercentage = (int)upvotePercentage;
            return blogPost;
        }

        // <summary>
        // Extension method to calculate Vote Ratio automatically with the given
        // Upvote and Downvote count
        // <returns>
        // BlogPost with the calculated Vote Ratio
        // </returns>
        // </summary>
        public static BlogPost CalculateVoteRatio(this BlogPost blogPost)
        {
            blogPost.Ratio = blogPost.Upvotes - blogPost.Downvotes;
            return blogPost;
        }
    }
}
