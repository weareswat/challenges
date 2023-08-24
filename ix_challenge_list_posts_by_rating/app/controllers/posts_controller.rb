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
    @post.upvotes += 1
    @post.save
    render json: { message: "Upvoted" }
  end

  def downvote
    @post.downvotes += 1
    @post.save
    render json: { message: "Downvoted" }
  end

  def index
    posts = Post.all.sort_by do |post|
      (post.upvotes + 1).to_f / (post.upvotes + post.downvotes + 2)
    end.reverse
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
