# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: "Star Wars" }, { name: "Lord of the Rings" }])
#   Character.create(name: "Luke", movie: movies.first)


Post.destroy_all
Post.create()
Post.create(upvotes: 1, downvotes: 0)
Post.create(upvotes: 0, downvotes: 1)
Post.create(upvotes: 10, downvotes: 6)
Post.create(upvotes: 5, downvotes: 10)
Post.create(upvotes: 100, downvotes:60)
Post.create(upvotes: 50, downvotes: 100)
Post.create(upvotes: 100, downvotes:0)
Post.create(upvotes: 0, downvotes: 100)
Post.create(upvotes: 300, downvotes: 100)