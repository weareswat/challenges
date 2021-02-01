require "test_helper"

class Api::V1::PostsControllerTest < ActionDispatch::IntegrationTest
  def setup
    @post_high = Post.create!({
      title: "New Title #{SecureRandom.uuid}",
      description: "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
      upvote: 600,
      downvote: 400
    })

    @post_low = Post.create!({
      title: "New Title #{SecureRandom.uuid}",
      description: "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
      upvote: 6,
      downvote: 4
    })
  end

  def test_api_v1_posts
    get api_v1_posts_path, as: :json
    assert_response :success
  end

  def test_highest_rated_posts_at_top
    get api_v1_posts_path, as: :json
    posts_ids_ordered = [@post_high, @post_low].pluck(:id)
    posts_ids_response = response.parsed_body["posts"].pluck("id")
    assert_equal posts_ids_response, posts_ids_ordered, "Posts Ordered"
  end
end
