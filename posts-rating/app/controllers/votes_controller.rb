class VotesController < ApplicationController
  before_action :set_post, only: %i[ create ]

  # POST /upvote/1
  # POST /downvote/1
  def create
    if @post.rate(Vote.new(vote_params.merge(vote_type: params[:vote_type])))
      render json: @post, status: :created, location: @post
    else
      render json: @post.errors, status: :unprocessable_entity
    end
  end

  private
    def set_post
      @post = Post.find(params[:post_id])
    end

    def vote_params
      params.require(:vote).permit(:post_id, :vote_type, :username)
    end
end
