class CreateUserVotes < ActiveRecord::Migration[7.0]
  def change
    create_table :user_votes do |t|
      t.references :post, foreign_key: true
      t.references :user, foreign_key: true

      t.timestamps
      t.index %i[user_id post_id], unique: true
    end
  end
end
