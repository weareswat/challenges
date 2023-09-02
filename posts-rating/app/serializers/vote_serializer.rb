class VoteSerializer < ActiveModel::Serializer
  attributes :id, :vote_type, :username
end
