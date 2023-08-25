class Post < ApplicationRecord
  validates :title, presence: true
  validates :upvotes, presence: true
  validates :downvotes, presence: true
  before_save :calculate_rating


  private

  def calculate_rating
    weight = (upvotes + downvotes + 2)
    self.rating = (upvotes + 1).to_f / (downvotes + 1).to_f * weight
  end
end
