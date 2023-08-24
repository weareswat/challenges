class PostsController < ApplicationController
  include Pagy::Backend

  before_action :set_post, only: %i[upvote downvote]

  # GET /posts
  def index
    @pagy, @posts = pagy(Post.all_posts_ranked_desc)

    render json: @posts
  end

  # PUT /upvote/1
  def upvote
    if @post.increment_upvotes
      render status: :ok
    else
      render json: @post.errors, status: 500
    end
  end

  # PUT /downvote/1
  def downvote
    if @post.increment_downvotes
      render status: :ok
    else
      render json: @post.errors, status: 500
    end
  end

  private
  def set_post
    @post = Post.find(params[:id])
  end
end
