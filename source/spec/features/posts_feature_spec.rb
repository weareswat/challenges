require 'rails_helper'

RSpec.feature 'Posts', type: :feature do
  scenario 'User views posts list' do
    post1 = Post.create!(title: 'First Post', upvotes: 10, downvotes: 2)
    post2 = Post.create!(title: 'Second Post', upvotes: 5, downvotes: 1)

    visit '/posts'

    expect(page).to have_content('Posts')
    expect(page).to have_content('First Post')
    expect(page).to have_content('Second Post')
    expect(page).to have_content('10') # upvotes
    expect(page).to have_content('2')  # downvotes
  end

  scenario 'User sees posts ordered by score' do
    low_score = Post.create!(title: 'Low Score Post', upvotes: 5, downvotes: 1)
    high_score = Post.create!(title: 'High Score Post', upvotes: 50, downvotes: 5)

    visit '/posts'

    # Check that posts are displayed
    expect(page).to have_content('High Score Post')
    expect(page).to have_content('Low Score Post')
  end

  scenario 'User sees empty state when no posts exist' do
    visit '/posts'

    expect(page).to have_content('Posts')
    expect(page).not_to have_content('data-post-id')
  end

  scenario 'User sees upvote and downvote buttons' do
    post = Post.create!(title: 'Test Post', upvotes: 5, downvotes: 2)

    visit '/posts'

    within("tr[data-post-id='#{post.id}']") do
      expect(page).to have_button('👍 Upvote')
      expect(page).to have_button('👎 Downvote')
    end
  end

  scenario 'User sees proper styling on buttons' do
    post = Post.create!(title: 'Test Post', upvotes: 5, downvotes: 2)

    visit '/posts'

    within("tr[data-post-id='#{post.id}']") do
      expect(page).to have_css('.btn.btn-success', text: '👍 Upvote')
      expect(page).to have_css('.btn.btn-danger', text: '👎 Downvote')
    end
  end

  scenario 'User sees vote counts and scores' do
    post = Post.create!(title: 'Test Post', upvotes: 15, downvotes: 3)

    visit '/posts'

    within("tr[data-post-id='#{post.id}']") do
      expect(page).to have_content('15') # upvotes
      expect(page).to have_content('3')  # downvotes
      expect(page).to have_content('18') # total votes
      expect(page).to have_content(post.score.to_f.round(6).to_s) # score
    end
  end
end
