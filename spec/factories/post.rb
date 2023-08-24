# frozen_string_literal: true

FactoryBot.define do
  factory :post do
    name { Faker::Movie.title }
    description { Faker::Movie.quote }
  end
end