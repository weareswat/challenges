class Post < ApplicationRecord
  validates :title, presence: true
  validates :upvotes, presence: true
  validates :downvotes, presence: true
end
