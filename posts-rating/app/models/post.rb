class Post < ApplicationRecord
  has_many :post

  validates :title, :content, presence: true
end
