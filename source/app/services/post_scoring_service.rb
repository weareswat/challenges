class PostScoringService
  #This service is used to calculate the score of a post. It reflects the Reddit-style logarithmic score.
  # Time decay factor in seconds (45000 seconds = 12.5 hours)
  TIME_DECAY_FACTOR = 45_000

  class << self
    # SQL version for database ordering
    def logarithmic_score_sql
      <<~SQL.squish
        CASE 
          WHEN (upvotes + downvotes) = 0 THEN 0
          ELSE SIGN(upvotes - downvotes) * 
               LN(GREATEST(ABS(upvotes - downvotes), 1)) * 
               LN(2 - LEAST(EXTRACT(EPOCH FROM (NOW() - created_at)) / 45000, 1))
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
    return 0.0 if post.total_votes.zero?

    vote_difference = post.upvotes - post.downvotes
    
    # Calculate sign exactly like SQL: 1 for positive, -1 for negative, 0 for zero
    sign = vote_difference > 0 ? 1 : vote_difference < 0 ? -1 : 0
    
    # Logarithm of absolute vote difference, using GREATEST logic like SQL
    log_vote_diff = Math.log([vote_difference.abs, 1].max)
    
    # Time decay factor (posts get older and lose ranking power)
    # Newer posts should have higher scores, so we invert the time factor
    created_time = post.created_at || Time.current
    time_diff_seconds = (Time.current - created_time).to_f
    time_factor = Math.log(2 - (time_diff_seconds / TIME_DECAY_FACTOR).clamp(0, 1))
    
    sign * log_vote_diff * time_factor
  end

  # Calculate total votes for a post
  def total_votes(post)
    post.upvotes + post.downvotes
  end
end
