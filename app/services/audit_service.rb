# frozen_string_literal: true

class AuditService
  def initialize(model:, old_changes:, new_changes:)
    @model = model
    @old_changes = old_changes
    @new_changes = new_changes
  end

  def perform
    old_changes_obj = serialize_changes(@old_changes)
    new_changes_obj = serialize_changes(@new_changes)

    args = assign_fields(old_changes_obj, new_changes_obj)

    Audit.create!(args)
  end

  private

  def serialize_changes(changes)
    changes_obj = []

    changes.each do |k, v|
      if v.is_a?(String) || v.is_a?(Integer)
        changes_obj << { "#{k}": v }
      else
        v.each do |value|
          changes_obj << { "#{k}.#{value.first}": value.second }
        end
      end
    end

    parse_json(changes_obj)
  end

  def parse_json(obj)
    JSON.parse(obj.inject { |k, v| k.merge(v) }.to_json)
  end

  def assign_fields(old_obj, new_obj)
    fields = []

    old_obj.each_with_index do |k, v|
      fields << {
        auditable_type: @model,
        auditable_id: new_obj.to_a[0][1],
        field: k[0],
        old: k[1],
        new: new_obj.to_a[v][1]
      }
    end

    fields.reject { |f| f[:field] == '_id' }
  end
end
