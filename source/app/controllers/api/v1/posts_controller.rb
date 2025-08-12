module Api
  module V1
    class PostsController < ApplicationController
      # Used this to so the controller won't require CSRF tokens for requests (specific to this case because I don't want to use any kind of authentication)
      protect_from_forgery with: :null_session

      before_action :set_post, only: %i[upvote downvote]

      def index
        posts = Post.ordered_by_score
        render json: posts.map { |p| serialize_post(p) }
      rescue StandardError => e
        log_error(e)
        render json: { error: 'Failed to fetch posts' }, status: :internal_server_error
      end

      def upvote
        @post.with_lock do
          @post.increment!(:upvotes)
        end
        render json: serialize_post(@post)
      rescue ActiveRecord::StaleObjectError => e
        log_error(e)
        render json: { error: 'Post was updated by another user. Please try again.' }, status: :conflict
      rescue StandardError => e
        log_error(e)
        render json: { error: 'Failed to upvote post' }, status: :internal_server_error
      end

      def downvote
        @post.with_lock do
          @post.increment!(:downvotes)
        end
        render json: serialize_post(@post)
      rescue ActiveRecord::StaleObjectError => e
        log_error(e)
        render json: { error: 'Post was updated by another user. Please try again.' }, status: :conflict
      rescue StandardError => e
        log_error(e)
        render json: { error: 'Failed to downvote post' }, status: :internal_server_error
      end

      private

      def set_post
        @post = Post.find(params[:id])
      rescue ActiveRecord::RecordNotFound => e
        log_error(e)
        render json: { error: 'Post not found' }, status: :not_found
      end

      def serialize_post(post)
        {
          id: post.id,
          title: post.title,
          upvotes: post.upvotes,
          downvotes: post.downvotes,
          total_votes: post.total_votes,
          score: post.score
        }
      end

      # Added this to log errors to the console to simplify the process of debugging
      # If was a bigger app, I would use a more robust logging system like Sentry or other.
      def log_error(exception)
        Rails.logger.error "API Error: #{exception.class} - #{exception.message}"
        Rails.logger.error exception.backtrace.join("\n") if Rails.env.development?
      end
    end
  end
end


