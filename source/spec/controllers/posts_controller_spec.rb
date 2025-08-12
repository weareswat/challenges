require 'rails_helper'

RSpec.describe PostsController, type: :controller do
  describe 'GET #index' do
    it 'assigns posts ordered by score' do
      post1 = Post.create!(title: 'Low Score', upvotes: 5, downvotes: 1)
      post2 = Post.create!(title: 'High Score', upvotes: 50, downvotes: 5)

      get :index

      expect(assigns(:posts)).to eq([post2, post1])
    end

    it 'renders the index template' do
      get :index
      expect(response).to render_template(:index)
    end

    it 'returns success status' do
      get :index
      expect(response).to have_http_status(:ok)
    end

    it 'handles empty posts list' do
      get :index
      expect(assigns(:posts)).to eq([])
    end
  end
end
