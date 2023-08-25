class Post < ApplicationRecord
  # Relationship
  has_many :user_votes, dependent: :destroy

  # Validations
  validates :name, :description, presence: { presence: true }

  # Scope
  scope :ordered_by_rating_and_votes, lambda {
    order(rating: :desc, vote_up: :desc)
  }

  # Callbacks
  before_update :calculate_rating

  def calculate_rating
    total_votes = vote_up + vote_down

    return 0.0 if total_votes.zero?

    self.rating = ((vote_up.to_f / total_votes) * 100).round(2)
  end
end
