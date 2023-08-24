module Api
  module V1
    class PostsController < ApplicationController
      def index
        posts = Post.all.order(:id)

        render status: :ok, json: { posts: }
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
        render status: :unprocessable_entity, json: { post: "Not found" }
      end

      private

      def post_params
        params.require(:post).permit(:name, :description)
      end
    end
  end
end