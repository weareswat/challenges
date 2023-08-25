class UpdateRatingForPosts < ActiveRecord::Migration[7.0]
  def change
    Post.find_each do |post|
      rating = (post.upvotes + 1).to_f / (post.upvotes + post.downvotes + 2)
      post.update_columns(rating: rating)
    end
  end
end
