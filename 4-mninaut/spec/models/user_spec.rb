require 'rails_helper'

RSpec.describe User, type: :model do
  context 'Create new users' do

    it 'Name is missing' do
      user = User.new
      expect(user).to_not be_valid
    end

    it 'Check users are created' do
      user_1 = User.create!(name: 'User Title 1')
      user_2 = User.create!(name: 'User Title 2')

      users = User.all

      expect(users)
      
    end

  end
end