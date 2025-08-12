require 'rails_helper'

RSpec.describe PostScoringService, type: :service do
  let(:service) { PostScoringService.new }
  let(:post) { Post.create!(title: "Test Post", upvotes: 10, downvotes: 2) }

  describe '.calculate_score' do
    it 'calculates score for a post' do
      score = PostScoringService.calculate_score(post)
      expect(score).to be_a(Float)
      expect(score).to be > 0
    end

    it 'returns 0 for posts with no votes' do
      post.update(upvotes: 0, downvotes: 0)
      score = PostScoringService.calculate_score(post)
      expect(score).to eq(0.0)
    end

    it 'handles posts with only downvotes' do
      post.update(upvotes: 0, downvotes: 5)
      score = PostScoringService.calculate_score(post)
      expect(score).to be < 0
    end
  end

  describe '#total_votes' do
    it 'calculates total votes correctly' do
      expect(service.total_votes(post)).to eq(12)
    end
  end



  describe '.logarithmic_score_sql' do
    it 'returns valid SQL' do
      sql = PostScoringService.logarithmic_score_sql
      expect(sql).to include('CASE')
      expect(sql).to include('SIGN')
      expect(sql).to include('LN')
    end
  end
end
