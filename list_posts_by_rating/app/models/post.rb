class Post < ApplicationRecord
  scope :all_posts_ranked_desc, -> do
    all.sort_by { |p| [p.upvote_ratio, p.score] }.reverse
  end

  def increment_upvotes
    with_lock do
      update(upvotes: upvotes + 1)
    end
  end

  def increment_downvotes
    with_lock do
      update(downvotes: downvotes + 1)
    end
  end

  def votes
    upvotes + downvotes
  end

  def score
    upvotes - downvotes
  end

  def upvote_ratio
    return 0 if votes.zero?

    return 0 if score.negative?

    (100*(upvotes.to_f/votes.to_f)).to_i
  end
end
