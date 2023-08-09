# frozen_string_literal: true

class CreateAudits < ActiveRecord::Migration[7.0]
  def change
    create_table :audits do |t|
      t.jsonb :audit_changes
      t.timestamps
    end
  end
end
