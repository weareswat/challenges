class Api::V1::UpvotesController < ApplicationController
  before_action :set_post

  def create
    begin
      Post::UpvoteCreateService.call(@post)
      render json: {}, status: 200
    rescue
      render json: { message: 'Upvote not registered' }, status: 400
    end
  end

  private

  def set_post
    @post = Post.find(params[:id])
  end
end
