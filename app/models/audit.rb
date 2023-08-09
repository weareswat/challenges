# frozen_string_literal: true

class Audit < ApplicationRecord
  scope :created_at, ->(start_date, end_date) { where('created_at::date >= ? AND created_at::date <= ?', start_date, end_date) }
  scope :order_by_created_at, -> { order(created_at: :asc) }
end
