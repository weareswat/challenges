require "test_helper"

class Post::DownvoteCreateServiceTest < ActiveSupport::TestCase
  def setup
    @post = Post.create!({
      title: "New Title #{SecureRandom.uuid}",
      description: "Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
      upvote: 600,
      downvote: 400
    })
  end

  def test_call_increment_post_downvote
    Post::DownvoteCreateService.call(@post)
    @post.reload
    assert_equal 401, @post.downvote
  end
end
