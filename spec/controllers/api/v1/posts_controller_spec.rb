require 'rails_helper'

RSpec.describe Api::V1::PostsController do
  let!(:new_post) { create(:post) }
  
  describe '#index' do
    it 'returns the post list' do
      get :index

      resp = response.parsed_body

      expect(resp['posts'].last['name']).to eq(new_post.name)
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
            name: "New test Post"
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
            name: "New test Post"
          }
        }
      end

      it 'return record not found' do
        expect(do_request).to have_http_status(:unprocessable_entity)
        expect(ActiveRecord::RecordNotFound).to be_present
      end
    end
  end
end