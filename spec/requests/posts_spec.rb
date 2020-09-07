require 'rails_helper'

RSpec.describe 'List Posts API', type: :request do
  let(:user) { create(:user) }
  let!(:user_post1) { create(:post, user_id: user.id, up_votes: 6, down_votes: 4, score: 0.6) }
  let!(:user_post2) { create(:post, user_id: user.id, up_votes: 60, down_votes: 40, score: 0.6) }

  describe 'POST /posts' do
    it 'creates a post' do
      params = {
        user_id: user.id,
        title: 'Post Title',
        body: Faker::Lorem.paragraph
      }

      post '/posts', params: params

      expect(json['user_name']).to eq(user.name)
      expect(json['title']).to eq('Post Title')
    end

    it 'tries to create a post without title and returns an error' do
      params = {
        user_id: user.id,
        body: Faker::Lorem.paragraph
      }

      post '/posts', params: params

      expect(json['error']).to eq('title' => ["can't be blank"])
    end
  end

  describe 'GET /posts' do
    it 'returns status 200 and list of user posts ordered by the highest score' do
      expected_response = [
        {
          id: user_post2.id,
          title: user_post2.title,
          body: user_post2.body,
          up_votes: user_post2.up_votes,
          down_votes: user_post2.down_votes,
          calc_score: "#{user_post2.score * 100}%",
          user_name: user_post2.user.name
        },
        {
          id: user_post1.id,
          title: user_post1.title,
          body: user_post1.body,
          up_votes: user_post1.up_votes,
          down_votes: user_post1.down_votes,
          calc_score: "#{user_post1.score * 100}%",
          user_name: user_post1.user.name
        }
      ]

      get '/posts', params: { user_id: user.id }

      expect(response.status).to be(200)
      expect(json).to eq(expected_response.as_json)
    end
  end

  describe 'PUT /posts' do
    it 'updates a post and returns' do
      params = {
        title: 'New Title',
        body: user_post1.body,
        user_id: user.id
      }

      expected_response = {
        id: user_post1.id,
        title: 'New Title',
        body: user_post1.body,
        up_votes: user_post1.up_votes,
        down_votes: user_post1.down_votes,
        calc_score: "#{user_post1.score * 100}%",
        user_name: user_post1.user.name
      }

      put "/posts/#{user_post1.id}", params: params

      expect(json).to eq(expected_response.as_json)
    end

    it 'up votes a post and returns the post updated' do
      put "/upvote/#{user_post1.id}", params: { type: 'up_vote' }

      expect(json['calc_score']).to eq("#{(7.0 / (7.0 + 4.0) * 100).round(2)}%")
    end

    it 'down votes a post and returns the post updated' do
      put "/downvote/#{user_post1.id}", params: { type: 'down_vote' }

      expect(json['calc_score']).to eq("#{(6.0 / (6.0 + 5.0) * 100).round(2)}%")
    end
  end
end
