require "test_helper"

class PostsControllerTest < ActionDispatch::IntegrationTest
  setup do
    @post = Post.create(id: 667784312, title: "Post", upvotes: 1, downvotes: 1)
    @ordered_post_1 = Post.create(id: 412341, title: "Post Title 1", upvotes: 100, downvotes: 50)
    @ordered_post_2 = Post.create(id: 32412432, title: "Post Title 2", upvotes: 10, downvotes: 5)
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
    put "/upvote/999999999"
    assert_response :not_found
    assert_equal JSON.parse(response.body)['error'], 'Post not found'
  end

  test "should return not found for downvote on invalid post" do
    put "/downvote/999999999"
    assert_response :not_found
    assert_equal JSON.parse(response.body)['error'], 'Post not found'
  end

  test "should upvote post" do
    assert_difference('@post.reload.upvotes', 1) do
      put "/upvote/#{@post.id}"
    end

    assert_response :success

    @post.reload
    assert_equal 2, @post.upvotes
  end

  test "should downvote post" do
    assert_difference('@post.reload.downvotes', 1) do
      put "/downvote/#{@post.id}"
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
  end
end
