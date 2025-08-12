FactoryBot.define do
  factory :post do
    sequence(:title) { |n| "Test Post #{n}" }
    upvotes { 0 }
    downvotes { 0 }
  end
end
