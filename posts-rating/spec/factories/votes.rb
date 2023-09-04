FactoryBot.define do
  factory :vote, class: Vote do
    username { Faker::Internet.username(specifier: 6..12, separators: ['.']) }

    transient do
      vote_type { [:up, :down].sample }
    end

    after(:build) do |vote, evaluator|
      vote.vote_type  = evaluator.vote_type
    end
  end
end
