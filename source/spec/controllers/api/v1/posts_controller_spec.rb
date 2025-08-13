require 'rails_helper'

RSpec.describe Api::V1::PostsController, type: :controller do
  describe 'GET #index' do
    it 'returns posts ordered by score as JSON' do
      post1 = Post.create!(title: 'Low Score', upvotes: 5, downvotes: 1)
      post2 = Post.create!(title: 'High Score', upvotes: 50, downvotes: 5)

      get :index

      expect(response).to have_http_status(:ok)
      expect(response.content_type).to include('application/json')

      json = JSON.parse(response.body)
      expect(json.length).to eq(2)
      expect(json.first['title']).to eq('High Score')
      expect(json.last['title']).to eq('Low Score')
    end

    it 'includes all required fields in JSON response' do
      post = Post.create!(title: 'Test Post', upvotes: 10, downvotes: 2)

      get :index

      json = JSON.parse(response.body)
      post_data = json.first

      expect(post_data).to include(
        'id', 'title', 'upvotes', 'downvotes', 'total_votes', 'score'
      )
      expect(post_data['title']).to eq('Test Post')
      expect(post_data['upvotes']).to eq(10)
      expect(post_data['downvotes']).to eq(2)
      expect(post_data['total_votes']).to eq(12)
      expect(post_data['score']).to be_a(Float)
    end
  end

  describe 'POST #upvote' do
    let(:test_post) { Post.create!(title: 'Test Post', upvotes: 5, downvotes: 2) }

    it 'increments upvotes' do
      expect {
        post :upvote, params: { id: test_post.id }
      }.to change { test_post.reload.upvotes }.by(1)
    end

    it 'returns updated post as JSON' do
      post :upvote, params: { id: test_post.id }

      expect(response).to have_http_status(:ok)
      expect(response.content_type).to include('application/json')

      json = JSON.parse(response.body)
      expect(json['upvotes']).to eq(6)
      expect(json['downvotes']).to eq(2)
      expect(json['total_votes']).to eq(8)
    end

    it 'returns 404 for non-existent post' do
      post :upvote, params: { id: 99999 }

      expect(response).to have_http_status(:not_found)
    end
  end

  describe 'POST #downvote' do
    let(:test_post) { Post.create!(title: 'Test Post', upvotes: 5, downvotes: 2) }

    it 'increments downvotes' do
      expect {
        post :downvote, params: { id: test_post.id }
      }.to change { test_post.reload.downvotes }.by(1)
    end

    it 'returns updated post as JSON' do
      post :downvote, params: { id: test_post.id }

      expect(response).to have_http_status(:ok)
      expect(response.content_type).to include('application/json')

      json = JSON.parse(response.body)
      expect(json['upvotes']).to eq(5)
      expect(json['downvotes']).to eq(3)
      expect(json['total_votes']).to eq(8)
    end

    it 'returns 404 for non-existent post' do
      post :downvote, params: { id: 99999 }

      expect(response).to have_http_status(:not_found)
    end
  end
end
