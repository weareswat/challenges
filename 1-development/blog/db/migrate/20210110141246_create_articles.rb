class CreateArticles < ActiveRecord::Migration[6.1]
  def change
    create_table :articles do |t|
      t.string :title
      t.text :body
      t.integer :upvotes
      t.integer :downvotes

      t.timestamps
    end
  end
end
