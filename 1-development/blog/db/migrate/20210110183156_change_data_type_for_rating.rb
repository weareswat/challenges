class ChangeDataTypeForRating < ActiveRecord::Migration[6.1]
  def self.up
    change_table :articles do |t|
      t.change :rating, :integer
    end
  end
  def self.down
    change_table :articles do |t|
      t.change :rating, :float
    end
  end
end
