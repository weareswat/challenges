class PostsController < ApplicationController
  before_action :find_post, only: [:upvote, :downvote]

  def create
    post = Post.new(post_params)

    if post.save
      render json: post, status: :created
    else
      render json: { errors: post.errors.full_messages }, status: :unprocessable_entity
    end
  end

  def upvote
    @post.update(upvotes: @post.upvotes + 1)
    render json: { message: "Upvoted" }
  end

  def downvote
    @post.update(downvotes: @post.downvotes + 1)
    render json: { message: "Downvoted" }
  end

  def index
    posts = Post.all.order(rating: :desc)
    render json: posts
  end

  private

  def post_params
    params.require(:post).permit(:title, :upvotes, :downvotes)
  end

  def find_post
    @post = Post.find_by(id: params[:id])

    unless @post
      render json: { error: 'Post not found' }, status: :not_found
    end
  end
end
