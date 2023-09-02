class Vote < ApplicationRecord
  enum type: { down: 0, up: 1 }
end
