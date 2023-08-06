require 'rails_helper'

RSpec.describe Post, type: :model do
  describe 'after_save' do
    context 'when up or down is changed' do
      it 'updates the assessment_rate' do
        post = Post.new(up: 5, down: 3)
        post.save!

        expect(post.assessment_rate).to eq(62.5) # 5 / (5 + 3) * 100
      end
    end

    context 'when up or down is not changed' do
      it 'does not update the assessment_rate' do
        post = Post.create!(up: 10, down: 2)
        post.update!(title: 'New Post')

        expect(post.assessment_rate.round(1)).to eq(83.3) # 10 / (10 + 2) * 100
      end
    end
  end

  describe 'default_scope' do
    it 'orders by assessment_rate in descending order' do
      post1 = Post.create!(title: 'Post 1', up: 5, down: 3, assessment_rate: 60)
      post2 = Post.create!(title: 'Post 2', up: 8, down: 2, assessment_rate: 80)
      post3 = Post.create!(title: 'Post 3', up: 3, down: 1, assessment_rate: 75)

      sorted_posts = Post.all

      expect(sorted_posts).to eq([post2, post3, post1])
    end

    it 'orders by up in descending order when assessment_rate is the same' do
      post1 = Post.create!(title: 'Post 1', up: 5, down: 3, assessment_rate: 80)
      post2 = Post.create!(title: 'Post 2', up: 8, down: 2, assessment_rate: 80)
      post3 = Post.create!(title: 'Post 3', up: 3, down: 1, assessment_rate: 80)

      sorted_posts = Post.all

      expect(sorted_posts).to eq([post2, post3, post1])
    end
  end
end