class AddVotesCountToPost < ActiveRecord::Migration[7.0]
  def change
    add_column :posts, :vote_up, :integer, default: 0
    add_column :posts, :vote_down, :integer, default: 0
  end
end
