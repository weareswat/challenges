class Post < ApplicationRecord
  has_many :votes, foreign_key: 'post_id', class_name: 'Vote', dependent: :delete_all

  validates :title, :content, presence: true

  scope :ordered_by_score, -> { sort_by { |p| -p.score } }

  def rate(vote)
    self.votes << vote

    save!
  end

  def up_votes_count
    count_votes_by_type(:up)
  end

  def down_votes_count
    count_votes_by_type(:down)
  end

  def total_votes_count
    self.votes.count
  end

  def score
    up_votes    = up_votes_count
    down_votes  = down_votes_count
    total_votes = up_votes + down_votes

    return 0 if total_votes.zero?

    return (down_votes.to_f / -0.1) * total_votes if up_votes.zero?
    return ((up_votes.to_f / 0.1) * total_votes).abs if down_votes.zero?

    return (up_votes.to_f / down_votes.to_f) * total_votes
  end

  private

  def count_votes_by_type(vote_type)
    self.votes.where(vote_type: vote_type).count
  end
end
