class User < ApplicationRecord
  validates_presence_of :name, presence: true
  
end
