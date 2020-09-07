require 'rails_helper'

RSpec.describe 'User Creation', type: :request do
  let!(:user) do
    create(
      :user,
      name: 'User',
      email: 'user@user.com',
      password: '123456'
    )
  end

  describe 'POST /user' do
    it 'creates user' do
      params = {
        name: 'Victor',
        email: 'victor@victor.com',
        password: '123456'
      }

      post '/signup', params: params

      expect(response.status).to be(200)
      expect(json['name']).to eq('Victor')
    end

    it 'returns error user already exists' do
      params = {
        name: 'User',
        email: 'user@user.com',
        password: '123456'
      }

      post '/signup', params: params

      expect(json['error']).to eq({ 'email' => ['has already been taken'] })
    end
  end

  describe 'GET /login' do
    it 'returns 200 and successfully logged in' do
      params = {
        email: 'user@user.com',
        password: '123456'
      }

      get '/login', params: params

      expect(response.status).to be(200)
      expect(json['name']).to eq('User')
    end

    it 'returns an error of invalid password or email' do
      params = {
        email: 'user@user.com',
        password: '1234'
      }

      get '/login', params: params

      expect(json['error']).to eq('Invalid email or password')
    end
  end
end
