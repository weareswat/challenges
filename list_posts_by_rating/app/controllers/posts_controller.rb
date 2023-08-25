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
    increment_vote :upvotes
  end

  # PUT /downvote/1
  def downvote
    increment_vote :downvotes
  end

  private
  def set_post
    @post = Post.find(params[:id])
  end

  # @param vote_type [Symbol] the type of vote to increment. Either :upvotes or :downvotes.
  def increment_vote(vote_type)
    result = @post.with_lock { @post.increment_vote(vote_type) }

    if result
      render status: :ok
    else
      log_error(@post, vote_type)

      render json: @post.errors, status: 500
    end
  end

  def log_error(post, vote_type)
    Rails.logger.error(
      <<~ERRORMSG
          Failed to increment #{vote_type} for Post id #{post.id}.\n
          Errors: #{post.errors.inspect}
          #{post.attributes.inspect}
      ERRORMSG
    )
  end
end
