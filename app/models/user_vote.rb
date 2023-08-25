class UserVote < ApplicationRecord
  # Relationship
  belongs_to :user
  belongs_to :post
end
