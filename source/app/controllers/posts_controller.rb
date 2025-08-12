class PostsController < ApplicationController
  def index
    @posts = Post.ordered_by_score
  rescue StandardError => e
    log_error(e)
    flash[:error] = "Failed to load posts. Please try again later."
    @posts = []
  end

  private

  def log_error(exception)
    Rails.logger.error "Posts Controller Error: #{exception.class} - #{exception.message}"
    Rails.logger.error exception.backtrace.join("\n") if Rails.env.development?
  end
end


