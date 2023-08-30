class CreatePosts < ActiveRecord::Migration[7.0]
  def change
    create_table :posts do |t|
      t.string :title
      t.text :content
      t.integer :upvotes
      t.integer :downvotes
      t.belongs_to :user, index: true

      t.timestamps
    end
  end
end
