require 'rails_helper'

RSpec.describe Vote, type: :model do
  subject { build(:vote) }

  let(:post) { create(:post) }

  before do
    subject.post = post
  end

  describe 'associations' do
    it { should belong_to(:post) }
  end

  describe 'create' do
    context 'has all valid parameters' do
      it "success on create" do
        expect(subject).to be_valid
      end
    end

    context 'has some invalid parameters' do
      it 'has not valid username' do
        subject.username = nil
        expect(subject.valid?).to be_falsey
      end
    end
  end



end
