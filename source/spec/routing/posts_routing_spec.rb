require 'rails_helper'

RSpec.describe 'Posts routing', type: :routing do
  describe 'UI routes' do
    it 'routes GET /posts to posts#index' do
      expect(get: '/posts').to route_to('posts#index')
    end
  end

  describe 'API routes' do
    it 'routes GET /api/v1/posts to api/v1/posts#index' do
      expect(get: '/api/v1/posts').to route_to('api/v1/posts#index')
    end

    it 'routes POST /api/v1/posts/:id/upvote to api/v1/posts#upvote' do
      expect(post: '/api/v1/posts/1/upvote').to route_to('api/v1/posts#upvote', id: '1')
    end

    it 'routes POST /api/v1/posts/:id/downvote to api/v1/posts#downvote' do
      expect(post: '/api/v1/posts/1/downvote').to route_to('api/v1/posts#downvote', id: '1')
    end
  end
end
