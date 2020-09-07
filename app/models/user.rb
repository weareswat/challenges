class User < ApplicationRecord
  validates_presence_of :name, :email
  validates_uniqueness_of :email
  has_secure_password
  has_many :posts, dependent: :destroy

  def list_posts
    posts.order(
      score: :desc,
      up_votes: :desc,
      id: :asc
    )
  end
end
