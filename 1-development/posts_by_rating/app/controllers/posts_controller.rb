class PostsController < ApplicationController
  before_action :set_post, only: %i[upvote downvote]
  rescue_from ActiveRecord::RecordNotFound, with: :error_render_method

  def index
    @posts = Post.all
    render json: @posts, status: :ok
  end

  def create
    @post = Post.new(post_params)

    if @post.save
      render json: @post, status: :created, location: @post
    else
      render json: @post.errors, status: :unprocessable_entity
    end
  end

  def upvote
    @post.update(up: @post.up + 1)
  end

  def downvote
    @post.update(down: @post.down + 1)
  end

  private

  def set_post
    @post = Post.find(params[:id])
  end

  def post_params
    params.require(:post).permit(:title)
  end

  def error_render_method(exception)
    render json: { message: exception.message }, status: :not_found
  end
end
