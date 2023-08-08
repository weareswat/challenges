require 'rails_helper'

RSpec.describe Post, type: :model do
  context 'Create new posts' do

    it 'Tilte is missing' do
      post = Post.new
      expect(post).to_not be_valid
    end

    it 'Check posts are created' do
      post_1 = Post.create!(title: 'Post Title 1')
      post_2 = Post.create!(title: 'Post Title 2')
      post_3 = Post.create!(title: 'Post Title 3')
      post_4 = Post.create!(title: 'Post Title 4')
      post_5 = Post.create!(title: 'Post Title 5')
      post_6 = Post.create!(title: 'Post Title 6')
      post_7 = Post.create!(title: 'Post Title 7')

      posts = Post.all

      expect(posts)
      
    end


  end
end