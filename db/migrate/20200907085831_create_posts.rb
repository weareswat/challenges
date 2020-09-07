class CreatePosts < ActiveRecord::Migration[5.2]
  def change
    create_table :posts do |t|
      t.string :title
      t.string :body
      t.integer :up_votes, default: 0
      t.integer :down_votes, default: 0
      t.float :score, default: 0.0
      t.references :user, foreign_key: true

      t.timestamps
    end
  end
end
