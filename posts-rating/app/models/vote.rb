class Vote < ApplicationRecord
  belongs_to :post

  enum vote_type: { down: 0, up: 1 }
end
