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
    audit_changes = []
    oldest_change = nil
    newest_change = nil
    result = []

    @audit.each_with_index do |k, v|
      audit_changes << k['audit_changes']
    end

    audit_changes.group_by { |ac| ac['_id'] }.each do |c|
      oldest_change = c.second.to_a.first
      newest_change = c.second.to_a.last
    end

    oldest_change.each do |k, v|
      result << { field: k, old: oldest_change[k], new: newest_change[k] }
    end

    result.reject { |f| f[:field] == '_id' }
  end
end
