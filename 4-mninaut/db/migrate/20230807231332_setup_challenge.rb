class SetupChallenge < ActiveRecord::Migration[7.0]
  enable_extension 'pgcrypto' unless extension_enabled?('pgcrypto')
  def change
    create_table :users, id: :uuid do |t|
      t.string :name, null: false

      t.timestamps null: false
    end

    create_table :posts, id: :uuid do |t|
      t.string :title, null: false

      t.timestamps null: false
    end

    create_table :votes, id: :uuid do |t|
      t.references :user, foreign_key: true, type: :uuid
      t.references :post, foreign_key: true, type: :uuid
      t.integer :kind, null: false #0 = up, #1 = down

      t.timestamps null: false
    end

  end
end
