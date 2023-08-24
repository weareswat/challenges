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

  describe '#positive_score' do
    it 'returns upvotes - downvotes' do
      expect(post.positive_score).to eq 0
    end
  end

  describe '#upvotes_ratio' do
    context 'when has no engagement' do
      subject { Fabricate :post, upvotes: 0, downvotes: 0 }
      it 'returns 0.0' do
        expect(subject.upvotes_ratio).to eq 0.0
      end
    end

    context 'when has engagement' do
      subject { Fabricate :post, upvotes: 60, downvotes: 40 }
      it 'returns the correct ratio' do
        expect(subject.upvotes_ratio).to eq 0.6
      end
    end
  end
end
