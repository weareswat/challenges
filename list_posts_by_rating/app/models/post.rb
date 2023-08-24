class Post < ApplicationRecord
  def increment_upvotes
    ActiveRecord::Base.transaction do
      update(upvotes: upvotes + 1)
    end
  end

  def increment_downvotes
    ActiveRecord::Base.transaction do
      update(downvotes: downvotes + 1)
    end
  end

  def votes
    upvotes + downvotes
  end

  def positive_score
    upvotes - downvotes
  end

  def average_rating
    return 0.0 if votes.zero?

    return 0.0 if positive_score.negative?

    upvotes.to_f/votes.to_f
  end

 scope :all_posts_ranked_desc, -> do
   all.sort_by { |p| [p.average_rating, p.positive_score] }.reverse
 end
end
