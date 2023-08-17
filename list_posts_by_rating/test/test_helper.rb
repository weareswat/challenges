ENV["RAILS_ENV"] ||= "test"
require_relative "../config/environment"
require "rails/test_help"

class ActiveSupport::TestCase
  # Run tests in parallel with specified workers
  parallelize(workers: :number_of_processors, with: :threads)

  # Setup all fixtures in test/fixtures/*.yml for all tests in alphabetical order.
  fixtures :all

  # Add more helper methods to be used by all tests here...
  def array_sorted?(array)

    return true if array.length == 0 || array.length == 1
    prev = array[0]

    for i in 1..array.length-1 do
      return false if prev < array[i]
      prev = array[i]
    end

    true
  end

  def posts_correctly_ordered?(posts)
    return true if posts.length == 0 || posts.length == 1
    prev_post = posts[0]

    for i in 1..posts.length-1 do

      return false if number_of_votes(prev_post) < number_of_votes(posts[i]) && prev_post["score"] == posts[i]["score"]
      prev_post = posts[i]
    end

    true
  end

  private

  def number_of_votes(post)
    post["upvotes"]+post["downvotes"]
end
end
