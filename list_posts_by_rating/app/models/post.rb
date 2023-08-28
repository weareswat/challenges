# frozen_string_literal: true

class Post < ApplicationRecord
  before_save :assign_rating

  scope :all_posts_ranked_desc, lambda {
    all.order(Arel.sql('rating DESC, (upvotes - downvotes) DESC'))
  }

  # Increments either upvotes or downvotes by 1 and also updates the rating.
  #
  # @param vote_type [Symbol] The kind of vote to increment. Either :upvotes or :downvotes.
  def increment_vote(vote_type)
    increment(vote_type)
    save!
  end

  def assign_rating
    self.rating = upvote_ratio
  end

  def upvote_ratio
    votes = upvotes + downvotes
    score = upvotes - downvotes

    return 0 if votes.zero?

    return 0 if score.negative?

    (100 * (upvotes.to_f / votes)).to_i
  end
end
