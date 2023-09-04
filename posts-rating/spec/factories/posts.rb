FactoryBot.define do
  factory :post, class: Post do
    title { Faker::Lorem.words(number: rand(2..6)).join(' ') }
    content { Faker::Lorem.paragraphs(number: rand(2..5)).join(' ') }

    trait :with_votes do
      transient do
        up { 0 }
        down { 0 }
      end

      after(:create) do |post, evaluator|
        evaluator.up.to_i.times do
          post.votes << build(:vote, vote_type: :up)
        end

        evaluator.down.to_i.times do
          post.votes << build(:vote, vote_type: :down)
        end
      end
    end

  end
end
