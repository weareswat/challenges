# spec/controllers/posts_controller_spec.rb
require 'rails_helper'

RSpec.describe PostsController, type: :controller do
  describe 'GET #index' do
    it 'returns a JSON list of all posts' do
      post1 = Post.create!(title: 'Post 1')
      post2 = Post.create!(title: 'Post 2')

      get :index
      parsed_response = JSON.parse(response.body)

      expect(response).to have_http_status(:ok)
      expect(parsed_response.length).to eq(2)
      expect(parsed_response[0]['title']).to eq('Post 1')
      expect(parsed_response[1]['title']).to eq('Post 2')
    end

    context 'when assesment_rate is the same' do
      it 'returns a JSON list ordered first by higher up' do
        Post.create!(title: 'Post 1', up: 6, down: 4, assessment_rate: 80)
        Post.create!(title: 'Post 2', up: 600, down: 400, assessment_rate: 80)

        get :index
        parsed_response = JSON.parse(response.body)

        expect(response).to have_http_status(:ok)        
        expect(parsed_response[0]['title']).to eq('Post 2')
        expect(parsed_response[1]['title']).to eq('Post 1')
      end
    end

    context 'when assesment_rate is different' do
      it 'returns a JSON list ordered first by higher rate' do
        Post.create!(title: 'Post 1', up: 5, down: 3, assessment_rate: 60)
        Post.create!(title: 'Post 2', up: 8, down: 2, assessment_rate: 80)
        Post.create!(title: 'Post 3', up: 3, down: 1, assessment_rate: 75)

        get :index
        parsed_response = JSON.parse(response.body)

        expect(response).to have_http_status(:ok)        
        expect(parsed_response[0]['title']).to eq('Post 2')
        expect(parsed_response[1]['title']).to eq('Post 3')
        expect(parsed_response[2]['title']).to eq('Post 1')
      end
    end
  end

  describe 'POST #create' do
    context 'with valid parameters' do
      it 'creates a new post' do
        post_params = { post: { title: 'New Post' } }

        expect {
          post :create, params: post_params
        }.to change(Post, :count).by(1)

        expect(response).to have_http_status(:created)
      end
    end
  end

  describe 'PUT #upvote' do
    it 'increases the upvote count of the post by 1' do
      post = Post.create!(title: 'Upvote Post', up: 5, down: 3)

      put :upvote, params: { id: post.id }

      expect(response).to have_http_status(:no_content)
      expect(post.reload.up).to eq(6)
    end
  end

  describe 'PUT #downvote' do
    it 'increases the downvote count of the post by 1' do
      post = Post.create!(title: 'Downvote Post', up: 5, down: 3)

      put :downvote, params: { id: post.id }

      expect(response).to have_http_status(:no_content)
      expect(post.reload.down).to eq(4)
    end
  end
end
