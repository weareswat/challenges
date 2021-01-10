class Article < ApplicationRecord
  validates :title, presence: true
  validates :body, presence: true, length: { minimum: 10 }
  
  def upvote
    increment(:upvotes)
    update_rating
  end

  def downvote
    increment(:downvotes)
    update_rating
  end
  
  def update_rating
    increment(:votes)
    update_attribute(:rating, read_attribute(:upvotes) - read_attribute(:downvotes))
  end

end
