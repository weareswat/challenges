class CreateVotes < ActiveRecord::Migration[7.0]
  def change
    create_table :votes do |t|
      t.integer :vote_type, default: 0
      t.string :username, null: false
      t.integer :post_id, null: false, index: true

      t.timestamps
    end
  end
end
