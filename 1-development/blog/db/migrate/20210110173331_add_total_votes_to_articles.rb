class AddTotalVotesToArticles < ActiveRecord::Migration[6.1]
  def change
    add_column :articles, :votes, :integer
  end
end
