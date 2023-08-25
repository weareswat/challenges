class UpdateRatingForPostsFix < ActiveRecord::Migration[7.0]
  def change
    Post.find_each do |post|
      weight = (post.upvotes + post.downvotes + 2)
      rating = ((post.upvotes + 1).to_f / (post.downvotes + 1)) * weight

      post.update_columns(rating: rating)
    end
  end
end
