RSpec.describe UpdateVotesByUser do
  let(:user) { create(:user) }
  let(:post) { create(:post) }
  let(:value) { 5 }
  let(:param) { :vote_up }

  subject(:update_votes) { described_class.new(post:, user:, value:, param:) }

  describe '#call' do
    it 'creates a user vote' do
      expect { update_votes.call }.to change(UserVote, :count).by(1)
    end

    it 'updates the post votes' do
      expect { update_votes.call }.to change { post.reload.vote_up }.by(6)
    end
  end
end
