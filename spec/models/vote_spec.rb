require 'rails_helper'

RSpec.describe Vote, type: :model do
  context 'Create new Votes' do

    it 'Post_id is missing' do
      vote = Vote.new(user_id: SecureRandom.uuid)
      expect(vote).to_not be_valid
    end

    it 'User_id is missing' do
      vote = Vote.new(post_id: SecureRandom.uuid)
      expect(vote).to_not be_valid
    end
    it 'kind is missing' do
      vote = Vote.new(kind: :up)
      expect(vote).to_not be_valid
    end

    it 'Check Votes are created' do
      post = create(:post)
      user = create(:user)

      create(:vote, user: user, post: post, kind: :up)

      votes = Vote.all

      expect(votes)
      
    end

  end
end