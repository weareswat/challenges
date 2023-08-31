require "test_helper"

class PostsControllerTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end
  test "index should return all posts sorted by ratio/upvotes" do
    user = User.create(username:"rms")
    ten = 10
    ten.times do
      Post.create(title: "title", content: "Lorem ipsum", upvotes: rand(500), downvotes: rand(500), user: user)
    end

    get "/posts"
    assert_response :success
    response_posts = JSON.parse(@response.body)
    stored_posts = Post.sorted

    ten.times do |time|
      assert_equal stored_posts[time].upvotes, response_posts[time]['upvotes']
      assert_equal stored_posts[time].downvotes, response_posts[time]['downvotes']
    end
  end

  test "upvotes endpoint should add 1 to post when called" do
    user = User.create(username: "rms")
    post = Post.create(title: "title", content: "Lorem ipsum", user: user)

    get "/upvote/" + post.id.to_s
    assert_response :success
    same_post = Post.find(post.id)
    assert_equal post.upvotes + 1, same_post.upvotes
    #making sure downvotes haven't changed
    assert_equal post.downvotes, same_post.downvotes
  end

  test "downvotes endpoint should add 1 to post when called" do
    user = User.create(username: "rms")
    post = Post.create(title: "title", content: "Lorem ipsum", user: user)

    get "/downvote/" + post.id.to_s
    assert_response :success
    same_post = Post.find(post.id)
    assert_equal post.downvotes + 1, same_post.downvotes
    #making sure upvotes haven't changed
    assert_equal post.upvotes, same_post.upvotes
  end
end
