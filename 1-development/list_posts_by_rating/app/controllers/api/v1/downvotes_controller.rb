class Api::V1::DownvotesController < ApplicationController
  before_action :set_post

  def create
    begin
      Post::DownvoteCreateService.call(@post)
      render json: {}, status: 200
    rescue
      render json: { message: 'Downvote not registered' }, status: 400
    end
  end

  private

  def set_post
    @post = Post.find(params[:id])
  end
end
