# frozen_string_literal: true

require 'rails_helper'

RSpec.describe Post, type: :model do
  fixtures :all

  context 'instance methods' do
    describe '#increment_vote' do
      let!(:post) { Fabricate :post, upvotes: 0, downvotes: 0, rating: 0 }

      context 'increment upvotes' do
        it 'Increments upvotes by 1 and updates the rating' do
          post.increment_vote(:upvotes)
          post.reload

          expect(post.upvotes).to eq 1
          expect(post.rating).to eq 100
        end
      end

      context 'increment downvotes' do
        it 'Increments downvotes by 1' do
          post.increment_vote(:downvotes)
          post.reload

          expect(post.reload.downvotes).to eq 1
        end
      end
    end

    describe '#assign_rating' do
      context 'when has no votes' do
        subject { Fabricate :post, upvotes: 0, downvotes: 0 }
        it 'returns 0.0' do
          expect(subject.rating).to eq 0
        end
      end

      context 'when positive score is negative' do
        subject { posts(:negative1) }
        it 'returns 0.0' do
          expect(subject.rating).to eq 0
        end
      end

      context 'when has votes' do
        subject { Fabricate :post, upvotes: 60, downvotes: 40 }
        it 'returns the correct ratio' do
          expect(subject.rating).to eq 60
        end
      end

      context 'when adding upvotes' do
        subject { Fabricate :post, upvotes: 60, downvotes: 40 }

        before do
          3.times { subject.increment_vote(:upvotes) }
          subject.reload
        end

        it 'returns the correct ratio' do
          expect(subject.rating).to eq 61
        end
      end

      context 'when adding downvotes' do
        subject { Fabricate :post, upvotes: 60, downvotes: 40 }

        before do
          subject.increment_vote(:downvotes)
          subject.reload
        end

        it 'returns the correct ratio' do
          expect(subject.rating).to eq 59
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
        posts(:negative2)
      ]
    end

    it 'return all posts ranked correctly' do
      expect(Post.all_posts_ranked_desc).to eq(expected_order)
    end
  end
end
