require 'rails_helper'

RSpec.describe Post, type: :model do
  context 'instance methods' do
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

  describe '.all_posts_ranking_desc' do
      let!(:only_upvotes) { Fabricate :post, upvotes: 600, downvotes: 0, content: 'I should rank 1st' }
      let!(:same_ratio1)  { Fabricate :post, upvotes: 600, downvotes: 400, content: 'I should rank 2nd' }
      let!(:same_ratio2)  { Fabricate :post, upvotes: 60, downvotes: 40, content: 'I should rank 3rd' }
      let!(:no_votes)     { Fabricate :post, upvotes: 0, downvotes: 0, content: 'I should rank 4th' }
      let!(:negative1)    { Fabricate :post, upvotes: 1, downvotes: 2, content: 'I should rank 5th' }
      let!(:negative2)    { Fabricate :post, upvotes: 0, downvotes: 2, content: 'I should rank 6th' }

      let(:expected_order) do
        [
          only_upvotes,
          same_ratio1,
          same_ratio2,
          no_votes,
          negative1,
          negative2,
        ]
      end

    it 'return all posts ranked correctly' do
      expect(Post.all_posts_ranking_desc).to match_array(expected_order)
    end
  end
end
