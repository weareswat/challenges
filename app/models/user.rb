class User < ApplicationRecord
  # Relationship
  has_many :user_votes, dependent: :destroy

  # Validations
  validates :name, presence: { presence: true }
end
