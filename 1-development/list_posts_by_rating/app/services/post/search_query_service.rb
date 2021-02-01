class Post
  class SearchQueryService
    # Rating Score implementation with lower bound of the Wilson score confidence interval for a Bernoulli parameter
    def self.call
      Post.find_by_sql(['SELECT id, title, description, upvote, downvote,
                                ((upvote + 1.9208) / (upvote + downvote) -
                                1.96 * SQRT((upvote * downvote) / (upvote + downvote) + 0.9604) /
                                (upvote + downvote)) / (1 + 3.8416 / (upvote + downvote))
                                AS lower_bound
                        FROM  posts
                        WHERE (upvote + downvote) > 0
                        ORDER BY lower_bound DESC'])
    end
  end
end
