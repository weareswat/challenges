class Post < ApplicationRecord
  validates :title, presence: true
  validates :upvotes, presence: true
  validates :downvotes, presence: true
  before_save :calculate_rating


  private

  def calculate_rating
    self.rating = (upvotes + 1).to_f / (upvotes + downvotes + 2)
  end
end
