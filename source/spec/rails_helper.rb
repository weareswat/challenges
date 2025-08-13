require 'spec_helper'
ENV['RAILS_ENV'] ||= 'test'
require_relative '../config/environment'
abort("The Rails environment is running in production mode!") if Rails.env.production?
require 'rspec/rails'
require 'capybara/rspec'

Dir[Rails.root.join('spec', 'support', '**', '*.rb')].sort.each { |f| require f }

begin
  ActiveRecord::Migration.maintain_test_schema!
rescue ActiveRecord::PendingMigrationError => e
  puts e.to_s.strip
  exit 1
end

RSpec.configure do |config|
  config.use_transactional_fixtures = true
  config.infer_spec_type_from_file_location!
  config.filter_rails_from_backtrace!
  
  # Capybara configuration
  config.include Capybara::DSL, type: :feature
  config.include Capybara::RSpecMatchers, type: :feature
end

# Capybara configuration
Capybara.default_driver = :rack_test
Capybara.javascript_driver = :selenium_chrome_headless
Capybara.server = :puma, { Silent: true }
