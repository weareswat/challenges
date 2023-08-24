# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: "Star Wars" }, { name: "Lord of the Rings" }])
#   Character.create(name: "Luke", movie: movies.first)

Post.create(upvotes: 600, downvotes: 0, content: 'I should rank 1st')
Post.create(upvotes: 600, downvotes: 400, content: 'I should rank 2nd')
Post.create(upvotes: 60, downvotes: 40, content: 'I should rank 3rd')
Post.create(upvotes: 0, downvotes: 0, content: 'I should rank 4th')
Post.create(upvotes: 1, downvotes: 2, content: 'I should rank 5th')
Post.create(upvotes: 0, downvotes: 2, content: 'I should rank 6th')
