class PostSerializer < ActiveModel::Serializer
  attributes :id, :title, :content, :up_votes, :down_votes, :score

  def up_votes
    object.up_votes_total
  end

  def down_votes
    object.down_votes_total
  end
end
