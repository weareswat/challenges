class PostSerializer < ActiveModel::Serializer
  attributes :id, :title, :content, :votes

  def votes
    up_votes   = object.up_votes_count
    down_votes = object.down_votes_count

    {
      score: object.score,
      total: up_votes + down_votes,
      up: up_votes,
      down: down_votes,
    }
  end
end
