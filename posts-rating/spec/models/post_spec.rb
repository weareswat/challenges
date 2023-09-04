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

        subject.increase_vote(vote)

        expect(subject.votes.count).to eq 1
      end

      it 'has many votes' do
        up_votes_total   = 8
        down_votes_total = 2

        post = create(:post, :with_votes, up: up_votes_total, down: down_votes_total)

        expect(post.votes).to_not be_empty
        expect(post.up_votes_total).to eq up_votes_total
        expect(post.down_votes_total).to eq down_votes_total
      end
    end
  end

  describe 'scoring' do
    context 'when post has no votes' do
      it 'score must equal to zero' do
        expect(subject.score).to eq 0
      end
    end

    context 'when post has votes' do
      it 'must have a score greater than zero' do
        expect(subject.score).to be > 0
      end
    end
  end

end
