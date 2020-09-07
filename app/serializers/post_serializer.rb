class PostSerializer < ActiveModel::Serializer
  attributes :id, :title, :body, :up_votes, :down_votes, :calc_score, :user_name

  def calc_score
    "#{(object.score * 100).round(2)}%"
  end

  def user_name
    object.user.name
  end
end
