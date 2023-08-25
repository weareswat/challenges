module Api
  module V1
    class PostsController < ApplicationController
      before_action :post, only: %i[vote_up vote_down]
      before_action :user, only: %i[vote_up vote_down]

      def index
        posts = Post.ordered_by_rating_and_votes

        render status: :ok, json: posts, each_serializer: PostSerializer
      end

      def create
        post = Post.new(post_params)

        if post.save
          render status: :created, json: { post: }
        else
          render status: :unprocessable_entity, json: { post: post.errors.full_messages }
        end
      end

      def update
        post = Post.find(params[:id])

        if post.update(post_params)
          render status: :ok, json: { post: }
        else
          render status: :unprocessable_entity, json: { post: post.errors.full_messages }
        end
      rescue ActiveRecord::RecordNotFound
        render status: :unprocessable_entity, json: { post: 'Not found' }
      end

      def vote_up
        UpdateVotesByUser.new(post:, user:, value: post.vote_up, param: 'vote_up').call

        render status: :ok, json: { post: }
      end

      def vote_down
        UpdateVotesByUser.new(post:, user:, value: post.vote_down, param: 'vote_down').call

        render status: :ok, json: { post: }
      end

      private

      def post_params
        params.require(:post).permit(:name, :description)
      end

      def post
        @post ||= Post.find(params[:post_id])
      rescue ActiveRecord::RecordNotFound
        render status: :unprocessable_entity, json: { post: 'Not found' }
      end

      def user
        @user ||= User.find(params[:user_id])
      rescue ActiveRecord::RecordNotFound
        render status: :unprocessable_entity, json: { user: 'Not found' }
      end
    end
  end
end
