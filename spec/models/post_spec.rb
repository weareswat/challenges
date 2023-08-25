require 'rails_helper'

RSpec.describe Post, type: :model do
  describe 'associations' do
    it { is_expected.to have_many(:user_votes).dependent(:destroy) }
  end

  describe 'validations' do
    it { is_expected.to validate_presence_of(:name) }
    it { is_expected.to validate_presence_of(:description) }
  end

  describe 'scope' do
    it 'orders posts by rating and votes' do
      post1 = create(:post, rating: 60, vote_up: 60, vote_down: 40)
      post2 = create(:post, rating: 60, vote_up: 6, vote_down: 4)
      post3 = create(:post, rating: 60, vote_up: 600, vote_down: 400)

      ordered_posts = Post.ordered_by_rating_and_votes.to_a

      expect(ordered_posts).to eq([post3, post1, post2])
    end
  end

  describe '#calculate_rating' do
    it 'calculates the rating based on vote_up and vote_down' do
      post = create(:post, vote_up: 30, vote_down: 10)
      post.calculate_rating
      expect(post.rating).to eq(75.0)
    end

    it 'calculates the rating as 0.0 when total votes are zero' do
      post = create(:post, vote_up: 0, vote_down: 0)
      post.calculate_rating
      expect(post.rating).to eq(0.0)
    end
  end
end
