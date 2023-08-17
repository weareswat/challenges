class PostsController < ApplicationController

    def create
        Post.create()
    end

    def index
        @posts = Post.all
        sorted_posts = @posts.sort_by do |post|
            [ post.score, post.number_of_votes ] 
        end
        sorted_posts.reverse!
        render :json => sorted_posts.to_json(:methods => [:score])
    end

    def upvote
        @post = Post.find(params[:id])
        @post.increment!(:upvotes, 1)
    end

    def downvote
        @post = Post.find(params[:id])
        @post.increment!(:downvotes, 1)
    end
end