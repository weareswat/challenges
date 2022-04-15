using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json.Serialization;
using System.Threading.Tasks;

namespace ListPostsByRating.Models
{
    public class BlogPost
    {
        public int Id { get; set; }
        public int Upvotes { get; set; }

        public int Downvotes { get; set; }

        // Upvote - Downvote
        public int Ratio { get; set; }

        // (Upvote / (Upvote + Downvote) ) * 100
        public int UpvotePercentage { get; set; }



    }
}
