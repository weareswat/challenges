require "test_helper"

class PostsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @post = Post.create(title: "Post", upvotes: 1, downvotes: 1)
    @ordered_post_1 = Post.create(title: "Post Title 1", upvotes: 600, downvotes: 400)
    @ordered_post_2 = Post.create(title: "Post Title 2", upvotes: 60, downvotes: 40)
    @ordered_post_3 = Post.create(title: "Post Title 3", upvotes: 6, downvotes: 4)
  end

  test "should create post" do
    assert_difference('Post.count') do
      post '/posts', params: { post: { title: 'New Test Post', upvotes: 0, downvotes: 0 } }, as: :json
    end

    assert_response :created
  end

  test "should not create post with invalid parameters" do
    assert_no_difference('Post.count') do
      post '/posts', params: { post: { invalid_param: 'Invalid', upvotes: 0, downvotes: 0 } }, as: :json
    end

    assert_response :unprocessable_entity
  end

  test "should return not found for upvote on invalid post" do
    put "/posts/999999999/upvote"
    assert_response :not_found
    assert_equal JSON.parse(response.body)['error'], 'Post not found'
  end

  test "should return not found for downvote on invalid post" do
    put "/posts/999999999/downvote"
    assert_response :not_found
    assert_equal JSON.parse(response.body)['error'], 'Post not found'
  end

  test "should upvote post" do
    assert_difference('@post.reload.upvotes', 1) do
      put "/posts/#{@post.id}/upvote"
    end

    assert_response :success

    @post.reload
    assert_equal 2, @post.upvotes
  end

  test "should downvote post" do
    assert_difference('@post.reload.downvotes', 1) do
      put "/posts/#{@post.id}/downvote"
    end

    assert_response :success

    @post.reload
    assert_equal 2, @post.downvotes
  end

  test "should get index" do
    get '/posts', as: :json
    assert_response :success

    response_data = JSON.parse(response.body)
    assert_equal response_data.length, Post.count
  end

  test "should get index and verify order" do
    get '/posts', as: :json
    assert_response :success

    response_data = JSON.parse(response.body)

    assert_equal @ordered_post_1.id, response_data[0]["id"]
    assert_equal @ordered_post_2.id, response_data[1]["id"]
    assert_equal @ordered_post_3.id, response_data[2]["id"]
  end
end
