class Api::V1::PostsController < ApplicationController
  def index
    begin
      posts = Post::SearchQueryService.call
      render json: { posts: posts }, status: 200
    rescue => exception
      render json: { message: exception.message }, status: 400
    end
  end
end
