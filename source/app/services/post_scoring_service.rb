class PostScoringService
  #This service is used to calculate the score of a post. It reflects the Reddit-style logarithmic score.

  class << self
    # SQL version for database ordering
    def logarithmic_score_sql
      <<~SQL.squish
        CASE 
          WHEN (upvotes + downvotes) = 0 THEN 0
          ELSE LOG10(GREATEST(ABS(upvotes - downvotes), 1)) + 
               SIGN(upvotes - downvotes) * (EXTRACT(EPOCH FROM created_at) - 1134028003) / 45000.0
        END
      SQL
    end

    # Calculate score for a single post
    def calculate_score(post)
      new.calculate_score(post)
    end

    # Calculate scores for multiple posts
    def calculate_scores(posts)
      posts.map { |post| [post, calculate_score(post)] }
    end

    # Sort posts by score
    def sort_by_score(posts)
      posts.sort_by { |post| -calculate_score(post) }
    end
  end

  # Instance method for calculating score
  def calculate_score(post)
    vote_diff = post.upvotes - post.downvotes
    sign = vote_diff <=> 0  # -1, 0, or 1
  
    # Log component (always positive or zero)
    log_vote_diff = Math.log10([vote_diff.abs, 1].max)
  
    # Reddit epoch: Dec 8, 2005
    reddit_epoch = Time.at(1134028003)
    time_since_reddit_epoch = (post.created_at || Time.current) - reddit_epoch
  
    # Time component, affected by vote direction
    time_score = sign * (time_since_reddit_epoch.to_f / 45000.0)
  
    # Final Reddit-style score
    log_vote_diff + time_score
  end

  # Calculate total votes for a post
  def total_votes(post)
    post.upvotes + post.downvotes
  end
end
