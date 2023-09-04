require 'rails_helper'

RSpec.describe Post, type: :model do
  subject { build(:post) }

  let(:vote) { build(:vote, post: subject) }

  describe 'associations' do
    it { should have_many(:votes) }
  end

  describe 'create' do
    context 'has all valid parameters' do
      it "success on create" do
        expect(subject).to be_valid
      end
    end

    context 'has some invalid parameter' do
      it 'has not valid title' do
        subject.title = nil
        expect(subject.valid?).to be_falsey
      end

      it 'has not valid content' do
        subject.content = nil
        expect(subject.valid?).to be_falsey
      end
    end
  end

  describe 'votes' do
    context 'when post has no votes' do
      it 'votes collection must be empty' do
        expect(subject.votes).to be_empty
      end
    end

    context 'when post has votes' do
      it 'has one vote' do
        expect(subject.votes).to be_empty

        subject.rate(vote)

        expect(subject.votes.count).to eq 1
      end

      it 'has many votes' do
        up_votes   = 8
        down_votes = 2

        post = create(:post, :with_votes, up: up_votes, down: down_votes)

        expect(post.votes).to_not be_empty
        expect(post.up_votes_count).to eq up_votes
        expect(post.down_votes_count).to eq down_votes
        expect(post.total_votes_count).to eq up_votes + down_votes
      end
    end
  end

  describe 'scoring' do
    context 'when post has no votes' do
      it 'score must be equal to zero' do
        expect(subject.score).to eq 0
      end
    end

    context 'when post has only negative votes' do
      before { subject.rate(build(:vote, post: subject, vote_type: :down)) }

      it 'must have a score lower than zero' do
        expect(subject.score).to be < 0
      end
    end

    context 'when user rate posts' do
      let(:post) { create(:post, :with_votes, up: 10, down: 5) }

      it 'score must be increased after up vote' do
        score_before_vote = post.score

        post.rate(build(:vote, post: post, vote_type: :up))

        score_after_vote = post.score

        expect(score_after_vote).to be > score_before_vote
      end

      it 'score must be decreased after down vote' do
        score_before_vote = post.score

        post.rate(build(:vote, post: post, vote_type: :down))

        score_after_vote = post.score

        expect(score_after_vote).to be < score_before_vote
      end
    end

    context 'when two posts have the same ratio between up and down votes' do
      context 'and posts have more than one up and down votes' do
        let(:post_hundred_votes) { create(:post, :with_votes, up: 60, down: 40) }
        let(:post_ten_votes) { create(:post, :with_votes, up: 6, down: 4) }

        it 'must have different score' do
          expect(post_hundred_votes.score).to be > post_ten_votes.score
        end
      end

      context 'and one of them has zero up or down vote' do
        let(:post_one_vote) { create(:post, :with_votes, up: 1, down: 0) }
        let(:post_seven_votes) { create(:post, :with_votes, up: 4, down: 3) }

        it 'must have different score' do
          expect(post_one_vote.score).to be > post_seven_votes.score
        end
      end
    end
  end

end
