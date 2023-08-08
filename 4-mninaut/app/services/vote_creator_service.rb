class VoteCreatorService
    def initialize(user_id, post_id, kind_vote)
      @user = user_id
      @post = post_id
      @vote = kind_vote
    end
  
    def upsert
      vote = Vote.where(user_id: @user, post_id: @post).take || Vote.new(user_id: @user, post_id: @post, kind: @vote)
      vote.update!({:kind => @vote})
      vote
    end
  
end