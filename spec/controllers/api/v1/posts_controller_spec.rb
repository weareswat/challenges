require 'rails_helper'

RSpec.describe Api::V1::PostsController do
  let!(:new_post) { create(:post) }

  describe '#index' do
    it 'returns the post list' do
      get :index

      expect(response.parsed_body).to be_present
      expect(response.status).to eq(200)
    end
  end

  describe '#create' do
    let(:params) { nil }
    let(:do_request) { post :create, params: }

    context 'with a valid params' do
      let(:params) do
        {
          post: {
            name: Faker::Movie.title,
            description: Faker::Movie.quote
          }
        }
      end

      it 'creates the post' do
        expect(do_request).to have_http_status(:created)
        expect(response.parsed_body['post']).to be_present
      end
    end

    context 'with invalid params' do
      let(:params) do
        {
          post: {
            name: Faker::Movie.title
          }
        }
      end

      it 'returns error message' do
        expect(do_request).to have_http_status(:unprocessable_entity)
        expect(response.parsed_body['post']).to contain_exactly("Description can't be blank")
      end
    end
  end

  describe '#update' do
    let(:params) { nil }
    let(:do_request) { put :update, params: }

    context 'with a valid params' do
      let(:params) do
        {
          id: new_post.id,
          post: {
            name: 'New test Post'
          }
        }
      end

      it 'update the post' do
        expect(do_request).to have_http_status(:success)
        expect(response.parsed_body['post']['name']).to eq('New test Post')
      end
    end

    context 'with invalid params' do
      let(:params) do
        {
          id: 'A',
          post: {
            name: 'New test Post'
          }
        }
      end

      it 'return record not found' do
        expect(do_request).to have_http_status(:unprocessable_entity)
        expect(ActiveRecord::RecordNotFound).to be_present
      end
    end
  end

  describe 'vote_up' do
    let!(:new_post) { create(:post) }
    let!(:user) { create(:user) }

    it 'increases the votes up count by 1' do
      expect do
        post :vote_up, params: { post_id: new_post.id, user_id: user.id }
      end.to change { new_post.reload.vote_up }.by(1)
    end

    it 'returns a successful response' do
      post :vote_up, params: { post_id: new_post.id, user_id: user.id }
      expect(response).to have_http_status(:ok)
    end
  end

  describe 'vote_down' do
    let!(:new_post) { create(:post) }
    let!(:user) { create(:user) }

    it 'increases the votes down count by 1' do
      expect do
        post :vote_down, params: { post_id: new_post.id, user_id: user.id }
      end.to change { new_post.reload.vote_down }.by(1)
    end

    it 'returns a successful response' do
      post :vote_down, params: { post_id: new_post.id, user_id: user.id }
      expect(response).to have_http_status(:ok)
    end
  end
end
