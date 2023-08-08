require 'rails_helper'

RSpec.describe PostsController, type: :request do
  context "Upsert" do

    it "Should create a up vote" do
      post = create(:post)
      user = create(:user)

      put "/upvote/#{post.id}"
        expect(assigns(:vote))
    end

    it "Should create a down vote" do
      post = create(:post)
      user = create(:user)
      
      put "/downvote/#{post.id}"
        expect(assigns(:vote))
    end

  end
end
