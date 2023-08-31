require "test_helper"

class PostTest < ActiveSupport::TestCase
  # test "the truth" do
  #   assert true
  # end
  test "wont create post without user" do
    post = Post.new
    assert_raises(ActiveRecord::RecordInvalid) do
      post.save!
    end
  end

  test "correct ratio calculations" do
    user = User.create(username: "alice_or_bob")
    post = Post.create(title: "title", content: "lorem ipsum", user: user, upvotes: 50, downvotes: 50)
    assert_equal 50, post.ratio

    post.upvotes += 10
    assert_equal 54.55, post.ratio
  end

  test "order posts by ratio" do
    user = User.create(username: "alice_or_bob")
    first_post = Post.create(title: "first", content: "lorem ipsum", user: user, upvotes: 5000, downvotes: 544)
    second_post = Post.create(title: "second", content: "lorem ipsum", user: user, upvotes: 100, downvotes: 8)
    third_post = Post.create(title: "third", content: "lorem ipsum", user: user, upvotes: 12, downvotes: 1300)

    posts = Post.sorted

    assert_equal second_post, posts[0]
    assert_equal first_post, posts[1]
    assert_equal third_post, posts[2]
  end

  test "order posts by upvotes when ratio is equal" do
    user = User.create(username: "alice_or_bob")
    first_post = Post.create(title: "first", content: "lorem ipsum", user: user, upvotes: 400, downvotes: 40)
    second_post = Post.create(title: "second", content: "lorem ipsum", user: user, upvotes: 2000, downvotes: 200)
    third_post = Post.create(title: "third", content: "lorem ipsum", user: user, upvotes: 60, downvotes: 6)

    posts = Post.sorted

    assert_equal second_post, posts[0]
    assert_equal first_post, posts[1]
    assert_equal third_post, posts[2]
  end

  test "when only two posts have same ratio among many posts" do
    user = User.create(username: "alice_or_bob")
    first_post = Post.create(title: "first", content: "lorem ipsum", user: user, upvotes: 600, downvotes: 40)
    second_post = Post.create(title: "second", content: "lorem ipsum", user: user, upvotes: 200000, downvotes: 900)
    third_post = Post.create(title: "third", content: "lorem ipsum", user: user, upvotes: 60, downvotes: 6)
    fourth_post = Post.create(title: "fourth", content: "lorem ipsum", user: user, upvotes: 10, downvotes: 200)
    fifth_post = Post.create(title: "fifth", content: "lorem ipsum", user: user, upvotes: 1300, downvotes: 130)

    #both third_post and fifth_post have the same ratio, but fifth_post should come up first since it has more upvotes

    posts = Post.sorted

    assert_equal second_post, posts[0]
    assert_equal first_post, posts[1]
    assert_equal fifth_post, posts[2]
    assert_equal third_post, posts[3]
    assert_equal fourth_post, posts[4]
  end

  test "posts with no reactions should be above negative posts" do
    user = User.create(username: "alice_or_bob")
    first_post = Post.create(title: "first", content: "lorem ipsum", user: user, upvotes: 600, downvotes: 40)
    second_post = Post.create(title: "second", content: "lorem ipsum", user: user, upvotes: 200000, downvotes: 900)
    third_post = Post.create(title: "third", content: "lorem ipsum", user: user)
    fourth_post = Post.create(title: "fourth", content: "lorem ipsum", user: user, upvotes: 10, downvotes: 200)
    fifth_post = Post.create(title: "fifth", content: "lorem ipsum", user: user)

    #both third_post and fifth_post have the same ratio, but fifth_post should come up first since it has more upvotes

    posts = Post.sorted

    assert_equal second_post, posts[0]
    assert_equal first_post, posts[1]
    assert_equal third_post, posts[2]
    assert_equal fifth_post, posts[3]
    assert_equal fourth_post, posts[4]
  end
end
