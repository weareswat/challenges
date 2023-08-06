class CreatePosts < ActiveRecord::Migration[7.0]
  def change
    create_table :posts do |t|
      t.string :title
      t.integer :up, default: 0
      t.integer :down, default: 0
      t.float :assessment_rate

      t.timestamps
    end
  end
end
