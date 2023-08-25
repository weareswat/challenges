class AddRatingToPost < ActiveRecord::Migration[7.0]
  def change
    add_column :posts, :rating, :float, default: 0.0
  end
end
