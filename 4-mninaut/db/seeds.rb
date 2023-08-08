# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: "Star Wars" }, { name: "Lord of the Rings" }])
#   Character.create(name: "Luke", movie: movies.first)

# Create description sample (10 posts with 6 up votes and 4 down votes)

User.create(id: '6003930e-1c6a-46d6-a4b7-afb1d8e8fe37', name: 'User test')
post = Post.create(title: "Post Title")

10.times do |i|
        user = User.create(name: "User name ##{i}")
    if i <= 5
        vote = Vote.create(post: post, user: user, kind: :up)
    else
        vote = Vote.create(post: post, user: user, kind: :down)
    end
end

post = Post.create(title: "Post Title 2")

1000.times do |i|
        user = User.create(name: "User name ##{i}")
    if i <= 599
        vote = Vote.create(post: post, user: user, kind: :up)
    else
        vote = Vote.create(post: post, user: user, kind: :down)
    end
end

post = Post.create(title: "Post Title 3")

100.times do |i|
        user = User.create(name: "User name ##{i}")
    if i <= 70
        vote = Vote.create(post: post, user: user, kind: :up)
    else
        vote = Vote.create(post: post, user: user, kind: :down)
    end
end