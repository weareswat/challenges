# frozen_string_literal: true

class FetchAuditService
  def initialize(audit:)
    @audit = audit
  end

  def perform
    serialize_audit
  end

  private

  def serialize_audit
    result = []

    @audit.group_by(&:field).each do |v|
      result << { field: v[0], old: v[1].first.old, new: v[1].last.new }
    end

    result
  end
end
