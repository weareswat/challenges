class MockController < ApplicationController
  def create_mocks
    alice = User.create(username: "alice")
    bob = User.create(username: "bob")
    charlie = User.create(username: "charlie")
    
    Post.create(title: "first post", content: "Lorem Ipsum and so on", user: alice)
    Post.create(title: "second post", content: "Lorem Ipsum and so on", user: bob)
    Post.create(title: "third post", content: "Lorem Ipsum and so on", user: charlie, upvotes: rand(500), downvotes: rand(500))
    Post.create(title: "fourth post", content: "Lorem Ipsum and so on", user: alice, upvotes: rand(500), downvotes: rand(500))
    Post.create(title: "fifth post", content: "Lorem Ipsum and so on", user: bob, upvotes: rand(500), downvotes: rand(500))
    head :ok
  end
end
