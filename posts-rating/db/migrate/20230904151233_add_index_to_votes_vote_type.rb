class AddIndexToVotesVoteType < ActiveRecord::Migration[7.0]
  def change
    add_index :votes, :vote_type
  end
end
