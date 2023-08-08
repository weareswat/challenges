require 'rails_helper'

RSpec.describe PostsController, type: :request do
  context "GET" do
    it "Should success and render post lists" do
      get '/posts'
        expect(response).to have_http_status(200)
    end

    it "Should list posts" do
      post = create(:post)
      user = create(:user)
      create(:vote, user: user, post: post, kind: :up)

      get '/posts'
        expect(assigns(:posts))
    end

  end
end
