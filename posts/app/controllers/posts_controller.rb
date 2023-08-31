class PostsController < ApplicationController

  def create
    user = User.find(username: params[:username])
    @post = Post.new(title: params[:title], content: params[:content], user: user)
    if @post.save!
      format.json {render json: @post}
    else
      render nothing: true, status: 400
    end
  end
  def index
    @posts = Post.sorted
    respond_to do |format|
      format.json { render json: @posts }
    end
  end

  private
  def post_params
    params.require(:username, :title, :content)
  end
end
