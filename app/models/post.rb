class Post < ApplicationRecord
  validates_presence_of :title, :body
  belongs_to :user

  def update_up_votes
    score = calculate_score(up_votes + 1, down_votes)

    update(
      up_votes: up_votes + 1,
      score: score
    )
  end

  def update_down_votes
    score = calculate_score(up_votes, down_votes + 1)

    update(
      down_votes: down_votes + 1,
      score: score
    )
  end

  private

  def calculate_score(up_votes, down_votes)
    return 0 if up_votes.zero? || down_votes.zero?

    up_votes.to_f / (up_votes + down_votes)
  end
end
