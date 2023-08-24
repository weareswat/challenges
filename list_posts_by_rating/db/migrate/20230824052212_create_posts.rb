class CreatePosts < ActiveRecord::Migration[7.0]
  def change
    create_table :posts do |t|
      t.integer :upvotes, default: 0
      t.integer :downvotes, default: 0
      t.string :content

      t.timestamps
    end
  end
end
