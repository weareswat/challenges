# frozen_string_literal: true

class Post < ApplicationRecord
  scope :all_posts_ranked_desc, lambda {
    all.order(Arel.sql('rating DESC, (upvotes - downvotes) DESC'))
  }

  # Increments either upvotes or downvotes by 1 and also updates the rating.
  #
  # @param vote_type [Symbol] The kind of vote to increment. Either :upvotes or :downvotes.
  def increment_vote(vote_type)
    new_votes_count = send(vote_type) + 1

    params = {}.tap do |p|
      p[vote_type] = new_votes_count
      p[:rating] = upvote_ratio(**Hash[:"new_#{vote_type}", new_votes_count])
    end

    update(**params)
  end

  def upvote_ratio(new_upvotes: upvotes, new_downvotes: downvotes)
    votes = new_upvotes + new_downvotes
    score = new_upvotes - new_downvotes

    return 0 if votes.zero?

    return 0 if score.negative?

    (100 * (new_upvotes.to_f / votes)).to_i
  end
end
