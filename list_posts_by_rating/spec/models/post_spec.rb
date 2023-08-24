require 'rails_helper'

RSpec.describe Post, type: :model do
  let!(:post) { Fabricate :post }

  describe '#increment_upvotes' do
    it 'Increments upvotes by 1' do
      post.increment_upvotes

      expect(post.reload.upvotes).to eq 2
    end
  end

  describe '#increment_downvotes' do
    it 'Increments downvotes by 1' do
      post.increment_downvotes

      expect(post.reload.downvotes).to eq 2
    end
  end

  describe '#engagement' do
    it 'returns upvotes + downvotes' do
      expect(post.engagement).to eq 2
    end
  end
end
