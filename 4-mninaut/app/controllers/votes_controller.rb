class VotesController < ApplicationController
  
  def upvote
    vote = VoteCreatorService.new(@current_user_id, params[:post_id], :up).upsert
    render json: vote, status: :ok
  end
  def downvote
    vote =  VoteCreatorService.new(@current_user_id, params[:post_id], :down).upsert
    render json: vote, status: :ok
  end
end