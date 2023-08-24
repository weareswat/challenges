require 'rails_helper'

RSpec.describe Post, type: :model do

  fixtures :all

  context 'instance methods' do
    let!(:post) { Fabricate :post }

    describe '#increment_upvotes' do
      it 'Increments upvotes by 1 and recalculates the rating' do
        post.increment_upvotes
        post.reload

        expect(post.upvotes).to eq 2
      end
    end

    describe '#increment_downvotes' do
      it 'Increments downvotes by 1' do
        post.increment_downvotes

        expect(post.reload.downvotes).to eq 2
      end
    end

    describe '#votes' do
      it 'returns upvotes + downvotes' do
        expect(post.votes).to eq 2
      end
    end

    describe '#score' do
      it 'returns upvotes - downvotes' do
        expect(post.score).to eq 0
      end
    end

    describe '#upvote_ratio' do
      context 'when has no votes' do
        subject { Fabricate :post, upvotes: 0, downvotes: 0 }
        it 'returns 0.0' do
          expect(subject.upvote_ratio).to eq 0
        end
      end

      context 'when positive score is negative' do
        subject { posts(:negative1) }
        it 'returns 0.0' do
          expect(subject.upvote_ratio).to eq 0
        end
      end

      context 'when has votes' do
        subject { Fabricate :post, upvotes: 60, downvotes: 40 }
        it 'returns the correct ratio' do
          expect(subject.upvote_ratio).to eq 60
        end
      end
    end
  end

  describe '.all_posts_ranked_desc' do
      # Using fixtures defined at spec/fixtures/posts.yml
      let(:expected_order) do
        [
          posts(:only_upvotes),
          posts(:same_ratio1),
          posts(:same_ratio2),
          posts(:no_votes),
          posts(:negative1),
          posts(:negative2),
        ]
      end

    it 'return all posts ranked correctly' do
      expect(Post.all_posts_ranked_desc).to eq(expected_order)
    end
  end
end
