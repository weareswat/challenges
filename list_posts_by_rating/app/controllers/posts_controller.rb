class PostsController < ApplicationController
  before_action :set_post, only: %i[upvote downvote]

  # GET /posts
  def index
    @posts = Post.all_posts_ranked_desc

    render json: @posts
  end

  # PATCH/PUT /posts/1
  def upvote
    if @post.increment_upvotes
      render status: :ok
    else
      render json: @post.errors, status: 500
    end
  end

  private
  # Use callbacks to share common setup or constraints between actions.
  def set_post
    @post = Post.find(params[:id])
  end
end
