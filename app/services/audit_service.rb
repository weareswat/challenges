# frozen_string_literal: true

class AuditService
  def initialize(audit_changes:)
    @audit_changes = audit_changes
  end

  def perform
    return nil if @audit_changes.empty?

    audit_changes_obj = serialize_changes(@audit_changes)
    Audit.create!(audit_changes: audit_changes_obj)
  end

  private

  def serialize_changes(changes)
    changes_obj = []

    changes.each do |k, v|
      if v.is_a?(String) || v.is_a?(Integer)
        changes_obj << { "#{k}": v }
      else
        changes_obj << iterate_through(k, v)
      end
    end

    parse_json(changes_obj.flatten)
  end

  def iterate_through(k, v)
    t = []

    v.each do |it_value|
      if it_value.second.is_a?(String) || it_value.second.is_a?(Integer)
        t << { "#{k}": it_value.second }
      else
        it_value.second.each do |it_k, it_v|
          t << { "#{it_value.first}.#{it_k}": it_v }
        end
      end
    end

    t
  end

  def parse_json(obj)
    obj = obj.reduce { |k3, v3| k3.merge(v3) }
    obj = obj.to_json

    JSON.parse(obj)
  end
end
