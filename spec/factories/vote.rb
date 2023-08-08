
FactoryBot.define do
    factory :vote do
        association :post
        association :user
        kind {rand(0..1)}
    end
end
