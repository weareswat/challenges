require 'rails_helper'

RSpec.describe Post, type: :model do
  describe 'validations' do
    it 'is valid with a title' do
      post = Post.new(title: 'Test Post')
      expect(post).to be_valid
    end

    it 'is invalid without a title' do
      post = Post.new(title: nil)
      expect(post).not_to be_valid
      expect(post.errors[:title]).to include("Title cannot be blank")
    end

    it 'has default values for upvotes and downvotes' do
      post = Post.create!(title: 'Test Post')
      expect(post.upvotes).to eq(0)
      expect(post.downvotes).to eq(0)
    end
  end

  describe '#total_votes' do
    it 'returns sum of upvotes and downvotes' do
      post = Post.new(upvotes: 10, downvotes: 5)
      expect(post.total_votes).to eq(15)
    end

    it 'returns zero when no votes' do
      post = Post.new(upvotes: 0, downvotes: 0)
      expect(post.total_votes).to eq(0)
    end
  end

  describe '#score' do
    context 'when no votes' do
      it 'returns zero' do
        post = Post.new(title: 'No votes')
        expect(post.score).to eq(0.0)
      end
    end

    context 'with positive vote difference' do
      it 'returns positive score' do
        post = Post.new(title: 'Positive votes', upvotes: 10, downvotes: 2)
        expect(post.score).to be > 0
      end

      it 'higher vote difference results in higher score' do
        high_diff = Post.new(title: 'High difference', upvotes: 100, downvotes: 10)
        low_diff = Post.new(title: 'Low difference', upvotes: 10, downvotes: 1)
        expect(high_diff.score).to be > low_diff.score
      end
    end

    context 'with negative vote difference' do
      it 'returns negative score' do
        post = Post.new(title: 'Negative votes', upvotes: 2, downvotes: 10)
        expect(post.score).to be < 0
      end
    end

    context 'with equal votes' do
      it 'returns zero score' do
        post = Post.new(title: 'Equal votes', upvotes: 5, downvotes: 5)
        expect(post.score).to eq(0.0)
      end
    end

    context 'time decay' do
      it 'newer posts have higher scores than older posts with same votes' do
        old_post = Post.create!(title: 'Old post', upvotes: 50, downvotes: 10, created_at: 5.days.ago)
        new_post = Post.create!(title: 'New post', upvotes: 50, downvotes: 10, created_at: 1.hour.ago)
        expect(new_post.score).to be > old_post.score
      end
    end
  end

  describe '.ordered_by_score' do
    it 'orders posts by logarithmic score in descending order' do
      post1 = Post.create!(title: 'Low score', upvotes: 5, downvotes: 1)
      post2 = Post.create!(title: 'High score', upvotes: 50, downvotes: 5)
      post3 = Post.create!(title: 'Negative score', upvotes: 1, downvotes: 10)

      ordered_posts = Post.ordered_by_score.to_a
      expect(ordered_posts.first).to eq(post2)
      expect(ordered_posts.last).to eq(post3)
    end

    it 'handles posts with zero votes' do
      post1 = Post.create!(title: 'No votes')
      post2 = Post.create!(title: 'Some votes', upvotes: 5, downvotes: 2)

      ordered_posts = Post.ordered_by_score.to_a
      expect(ordered_posts.first).to eq(post2)
      expect(ordered_posts.last).to eq(post1)
    end
  end

  describe 'database constraints' do
    it 'enforces non-null title' do
      expect {
        Post.create!(title: nil)
      }.to raise_error(ActiveRecord::RecordInvalid)
    end

    it 'enforces non-null upvotes' do
      expect {
        Post.create!(title: 'Test', upvotes: nil)
      }.to raise_error(ActiveRecord::RecordInvalid)
    end

    it 'enforces non-null downvotes' do
      expect {
        Post.create!(title: 'Test', downvotes: nil)
      }.to raise_error(ActiveRecord::RecordInvalid)
    end
  end
end


