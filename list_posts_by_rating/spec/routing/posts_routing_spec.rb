# frozen_string_literal: true

require 'rails_helper'

RSpec.describe PostsController, type: :routing do
  describe 'routing' do
    it 'routes to #index' do
      expect(get: '/posts').to route_to('posts#index')
    end

    it 'routes to #upvote' do
      expect(put: '/upvote/1').to route_to('posts#upvote', id: '1')
    end

    it 'routes to #downvote' do
      expect(put: '/downvote/1').to route_to('posts#downvote', id: '1')
    end
  end
end
