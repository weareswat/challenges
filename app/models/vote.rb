class Vote < ApplicationRecord
  belongs_to :post
  belongs_to :user

  enum kind: [:up, :down]
end
