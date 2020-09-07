FactoryBot.define do
  factory :post do
    title { Faker::Lorem.word }
    body { Faker::Lorem.paragraph }
    up_votes { 0 }
    down_votes { 0 }
    score { 0 }
    user_id { nil }
  end
end
