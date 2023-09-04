class Post < ApplicationRecord
  has_many :votes, foreign_key: 'post_id', class_name: 'Vote', dependent: :delete_all

  validates :title, :content, presence: true

  scope :ordered_by_score, -> { sort_by { |p| -p.score } }

  def increase_vote(vote)
    self.votes << vote

    save!
  end

  def up_votes_count
    count_votes(vote_type: :up)
  end

  def down_votes_count
    count_votes(vote_type: :down)
  end

  def total_votes_count
    up_votes_count + down_votes_count
  end

  def score
    up_votes   = self.up_votes_count
    down_votes = self.down_votes_count

    (up_votes.to_f / down_votes.to_f) * (up_votes + down_votes)
  end

  private

  def count_votes(vote_type:)
    self.votes.where(vote_type: vote_type).count
  end
end
