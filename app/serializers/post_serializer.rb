class PostSerializer < ActiveModel::Serializer
  attributes :id, :name, :description, :vote_up, :vote_down, :rating

  def rating
    "#{object.rating}%"
  end
end
