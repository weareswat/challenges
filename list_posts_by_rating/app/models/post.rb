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

  def engagement
    upvotes + downvotes
  end

  def positive_score
    upvotes - downvotes
  end

  def upvotes_ratio
    return 0.0 if engagement.zero?

    upvotes.to_f/engagement.to_f
  end

 scope :all_posts_ranked_desc, -> do
   all.sort_by { |p| [p.upvotes_ratio, p.positive_score] }
 end
end
