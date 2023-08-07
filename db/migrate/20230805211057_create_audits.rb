# frozen_string_literal: true

class CreateAudits < ActiveRecord::Migration[7.0]
  def change
    create_table :audits do |t|
      t.references :auditable, polymorphic: true, null: false
      t.string :field
      t.string :old
      t.string :new
      t.timestamps
    end
  end
end
