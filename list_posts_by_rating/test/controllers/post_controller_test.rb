require "test_helper"
class PostControllerTest < ActionDispatch::IntegrationTest 
   

    test "creates post" do
        post "/posts"

        assert_response :success
    end

    test "index should return a list" do
        get "/posts"
        result = JSON.parse(response.body)

        assert_kind_of(Array, result)
        assert_response :success
    end

    test "index should return a list ordered by score" do
        get "/posts"
        result = JSON.parse(response.body)
        scores = result.map{|x| x["score"]}

        assert(array_sorted?(scores))
        assert_response :success
    end

    test "index array should not be sorted" do
        get "/posts"
        result = JSON.parse(response.body)
        scores = result.map{|x| x["score"]}

        assert_not(array_sorted?(scores.reverse!))
        assert_response :success
    end 

    test "index posts with higher vote counts should take precedence" do
        get "/posts"
        result = JSON.parse(response.body)

        assert(posts_correctly_ordered?(result))
        assert_response :success
    end

    test "upvote" do
        post = Post.last
        old_upvotes = post.upvotes
        put "/posts/#{post.id}/upvote"
        post.reload

        assert_response :success
        assert_equal(old_upvotes+1,post.upvotes)
    end

    test "downvote" do
        post = Post.last
        old_downvotes = post.downvotes
        put "/posts/#{post.id}/downvote"
        post.reload

        assert_response :success
        assert_equal(old_downvotes+1,post.downvotes)
    end

end