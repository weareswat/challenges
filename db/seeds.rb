require 'factory_bot_rails'

puts "-- Seeding Posts"

FactoryBot.create_list(:post, 10)

puts "-- Seeding Users"

FactoryBot.create_list(:user, 10)