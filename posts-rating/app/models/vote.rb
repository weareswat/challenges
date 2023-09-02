class Vote < ApplicationRecord
  enum vote_type: { down: 0, up: 1 }
end
