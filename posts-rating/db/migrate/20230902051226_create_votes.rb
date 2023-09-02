class CreateVotes < ActiveRecord::Migration[7.0]
  def change
    create_table :votes do |t|
      t.integer :type, default: 0
      t.string :username

      t.timestamps
    end
  end
end
