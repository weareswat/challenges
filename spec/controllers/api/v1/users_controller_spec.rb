require 'rails_helper'

RSpec.describe Api::V1::UsersController do
  let!(:user) { create(:user) }
  
  describe '#index' do
    it 'returns the user list' do
      get :index

      resp = response.parsed_body

      expect(resp['users'].last['name']).to eq(user.name)
      expect(response.status).to eq(200) 
    end
  end

  describe '#create' do
    let(:params) { nil }
    let(:do_request) { post :create, params: }
    
    context 'with a valid params' do
      let(:params) do
        {
          user: {
            name: Faker::Name.name
          }
        }
      end

      it 'creates the user' do
        expect(do_request).to have_http_status(:created)
        expect(response.parsed_body['user']).to be_present
      end
    end

    context 'with invalid params' do
      let(:params) do
        {
          user: {
            name: nil
          }
        }
      end

      it 'returns error message' do
        expect(do_request).to have_http_status(:unprocessable_entity)
        expect(response.parsed_body['user']).to contain_exactly("Name can't be blank")
      end
    end
  end
end