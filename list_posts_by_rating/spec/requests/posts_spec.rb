# frozen_string_literal: true

require 'rails_helper'

RSpec.describe '/posts', type: :request do
  fixtures :posts

  describe 'GET /index' do
    it 'renders a successful response' do
      get posts_url, as: :json
      expect(response).to be_successful
    end
  end

  describe 'PUT /upvote' do
    let!(:my_post) { Fabricate :post, upvotes: 0, downvotes: 0 }

    context 'success' do
      it 'increments post upvotes by 1' do
        put post_upvote_url(my_post), as: :json
        my_post.reload
        expect(my_post.upvotes).to eq 1
        expect(my_post.downvotes).to eq 0
        expect(response).to be_successful
      end
    end

    context 'internal server error' do
      before { allow_any_instance_of(Post).to receive(:increment_vote).and_return(nil) }

      it 'returns a 500 error' do
        put post_upvote_url(my_post), as: :json
        expect(response).to have_http_status(500)
      end
    end
  end

  describe 'PUT /downvote' do
    let!(:my_post) { Fabricate :post, upvotes: 0, downvotes: 0 }

    context 'success' do
      it 'increments post downvotes by 1' do
        put post_downvote_url(my_post), as: :json
        my_post.reload
        expect(my_post.upvotes).to eq 0
        expect(my_post.downvotes).to eq 1
        expect(response).to be_successful
      end
    end

    context 'internal server error' do
      before { allow_any_instance_of(Post).to receive(:increment_vote).and_return(nil) }

      it 'returns a 500 error' do
        put post_downvote_url(my_post), as: :json
        expect(response).to have_http_status(500)
      end
    end
  end
end
