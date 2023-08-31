require "test_helper"

class UserControllerTest < ActionDispatch::IntegrationTest
  # test "the truth" do
  #   assert true
  # end
  test "should create user" do
    testcase_username = "bob_or_alice"
    data = {username: testcase_username}
    post "/users", params: data
    assert_response :success

    user = User.first
    assert_equal testcase_username, user.username
  end

  test "should list all users" do
    six = 6
    six.times do |i|
      User.create(username: i.to_s)
    end
    get "/users"
    assert_response :success
    assert_equal six, JSON.parse(@response.body).length
  end
end
