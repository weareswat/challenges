class Post < ApplicationRecord
  validates_presence_of :name, :description, presence: true
end