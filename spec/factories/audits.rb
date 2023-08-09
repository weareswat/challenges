# frozen_string_literal: true

FactoryBot.define do
  factory :audit do
    created_at { Date.today }
    updated_at { Date.today }

    trait :first_change do
      audit_changes do
        {'_id': 1, 'name': 'Bruce Norries', 'address.street': 'Some street'}
      end
    end

    trait :second_change do
      audit_changes do
        {'_id': 1, 'name': 'Bruce Willis', 'address.street': 'Nakatomi Plaza'}
      end
    end

    trait :third_change do
      audit_changes do
        {'_id': 1, 'name': 'Bruce Whatsyourname', 'address.street': 'Whatever street'}
      end
    end
  end
end
