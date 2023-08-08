class PostsController < ApplicationController
  
  def index
    posts = PostListService.new.by_rating
    render json: posts, status: :ok
  end
end