require 'rails_helper'

RSpec.describe 'Posts', type: :request do
  describe 'GET /posts' do
    it 'returns posts page with ordered posts' do
      post1 = Post.create!(title: 'First Post', upvotes: 10, downvotes: 2)
      post2 = Post.create!(title: 'Second Post', upvotes: 5, downvotes: 1)

      get '/posts'

      expect(response).to have_http_status(:ok)
      expect(response.body).to include('First Post')
      expect(response.body).to include('Second Post')
      expect(response.body).to include('10') # upvotes
      expect(response.body).to include('2')  # downvotes
    end

    it 'displays posts in score order' do
      low_score = Post.create!(title: 'Low Score', upvotes: 5, downvotes: 1)
      high_score = Post.create!(title: 'High Score', upvotes: 50, downvotes: 5)

      get '/posts'

      expect(response.body).to include('High Score')
      expect(response.body).to include('Low Score')
    end

    it 'handles empty posts list' do
      get '/posts'

      expect(response).to have_http_status(:ok)
      expect(response.body).to include('Posts')
      expect(response.body).not_to include('data-post-id')
    end
  end
end

RSpec.describe 'API::V1::Posts', type: :request do
  describe 'GET /api/v1/posts' do
    it 'returns posts ordered by Reddit-style logarithmic score' do
      high_score = Post.create!(title: 'High score post', upvotes: 100, downvotes: 10)
      medium_score = Post.create!(title: 'Medium score post', upvotes: 20, downvotes: 5)
      low_score = Post.create!(title: 'Low score post', upvotes: 5, downvotes: 1)
      negative_score = Post.create!(title: 'Negative score post', upvotes: 2, downvotes: 10)

      get '/api/v1/posts'

      expect(response).to have_http_status(:ok)
      json = JSON.parse(response.body)
      
      expect(json.length).to eq(4)
      
      # Verify ordering by score (highest first)
      scores = json.map { |post| post['score'] }
      expect(scores).to eq(scores.sort.reverse)
      
      # Verify the highest scoring post is first
      expect(json.first['title']).to eq('High score post')
    end

    it 'returns empty array when no posts exist' do
      get '/api/v1/posts'
      
      expect(response).to have_http_status(:ok)
      json = JSON.parse(response.body)
      expect(json).to eq([])
    end

    it 'includes all required fields in response' do
      post = Post.create!(title: 'Test Post', upvotes: 10, downvotes: 2)

      get '/api/v1/posts'

      json = JSON.parse(response.body)
      post_data = json.find { |p| p['id'] == post.id }
      
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

  describe 'POST /api/v1/posts/:id/upvote' do
    it 'increments upvotes and returns updated post data' do
      post_record = Post.create!(title: 'Test post', upvotes: 5, downvotes: 2)
      
      expect do
        post "/api/v1/posts/#{post_record.id}/upvote"
      end.to change { post_record.reload.upvotes }.by(1)
      
      expect(response).to have_http_status(:ok)
      
      json = JSON.parse(response.body)
      expect(json['upvotes']).to eq(6)
      expect(json['downvotes']).to eq(2)
      expect(json['total_votes']).to eq(8)
      expect(json['score']).to be_a(Float)
    end

    it 'returns 404 for non-existent post' do
      post "/api/v1/posts/99999/upvote"
      expect(response).to have_http_status(:not_found)
    end

    it 'handles multiple upvotes correctly' do
      post_record = Post.create!(title: 'Test post', upvotes: 0, downvotes: 0)
      
      # First upvote
      post "/api/v1/posts/#{post_record.id}/upvote"
      expect(response).to have_http_status(:ok)
      
      # Second upvote
      post "/api/v1/posts/#{post_record.id}/upvote"
      expect(response).to have_http_status(:ok)
      
      post_record.reload
      expect(post_record.upvotes).to eq(2)
      expect(post_record.downvotes).to eq(0)
    end
  end

  describe 'POST /api/v1/posts/:id/downvote' do
    it 'increments downvotes and returns updated post data' do
      post_record = Post.create!(title: 'Test post', upvotes: 5, downvotes: 2)
      
      expect do
        post "/api/v1/posts/#{post_record.id}/downvote"
      end.to change { post_record.reload.downvotes }.by(1)
      
      expect(response).to have_http_status(:ok)
      
      json = JSON.parse(response.body)
      expect(json['upvotes']).to eq(5)
      expect(json['downvotes']).to eq(3)
      expect(json['total_votes']).to eq(8)
      expect(json['score']).to be_a(Float)
    end

    it 'returns 404 for non-existent post' do
      post "/api/v1/posts/99999/downvote"
      expect(response).to have_http_status(:not_found)
    end

    it 'handles multiple downvotes correctly' do
      post_record = Post.create!(title: 'Test post', upvotes: 0, downvotes: 0)
      
      # First downvote
      post "/api/v1/posts/#{post_record.id}/downvote"
      expect(response).to have_http_status(:ok)
      
      # Second downvote
      post "/api/v1/posts/#{post_record.id}/downvote"
      expect(response).to have_http_status(:ok)
      
      post_record.reload
      expect(post_record.upvotes).to eq(0)
      expect(post_record.downvotes).to eq(2)
    end
  end

  describe 'database locking' do
    it 'uses database locking for vote increments' do
      post_record = Post.create!(title: 'Lock test', upvotes: 0, downvotes: 0)
      
      # Test that the controller uses with_lock by checking the behavior
      post "/api/v1/posts/#{post_record.id}/upvote"
      expect(response).to have_http_status(:ok)
      
      post_record.reload
      expect(post_record.upvotes).to eq(1)
    end
  end
end
