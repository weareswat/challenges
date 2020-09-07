class PostsController < ApplicationController
  def index
    render json: { success: false } unless current_user.id == params[:user_id].to_i

    @posts = current_user.list_posts
    render json: @posts
  end

  def create
    render json: { success: false } unless current_user.id == params[:user_id].to_i

    @post = Post.new(post_params)

    if @post.save
      render json: @post
    else
      render json: { error: @post.errors }
    end
  end

  def update
    render json: { success: false } unless current_user.id == params[:user_id].to_i

    @post = Post.find_by(id: params[:id])

    if @post.update(post_params)
      render json: @post
    else
      render json: { error: @post.errors }
    end
  end

  def upvote
    @post = Post.find_by(id: params[:id])

    if @post.update_up_votes
      render json: @post
    else
      render json: { error: @post.errors }
    end
  end

  def downvote
    @post = Post.find_by(id: params[:id])

    if @post.update_down_votes
      render json: @post
    else
      render json: { error: @post.errors }
    end
  end

  private

  def post_params
    params.permit(:user_id, :title, :body, :up_votes, :down_votes)
  end
end
