# frozen_string_literal: true

class AddRatingIndexToPosts < ActiveRecord::Migration[7.0]
  def change
    add_index :posts, 'rating DESC, (upvotes - downvotes) DESC', name: 'index_posts_rating'
  end
end
