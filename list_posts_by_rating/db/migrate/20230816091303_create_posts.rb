class CreatePosts < ActiveRecord::Migration[7.0]
  def change
    create_table :posts do |t|
      t.integer :upvotes, default: 0, null: false
      t.integer :downvotes, default: 0, null: false

      t.timestamps
    end
  end
end
