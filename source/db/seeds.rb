# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the bin/rails db:seed command (or created alongside the database with db:setup).
#
# Example:
#
#   ["Action", "Comedy", "Drama", "Horror"].each do |genre_name|
#     MovieGenre.find_or_create_by!(name: genre_name)
#   end

# Create test posts with different vote patterns to demonstrate Wilson score ranking
Post.create!([
  { title: "High volume, good ratio (600/400)", upvotes: 600, downvotes: 400 },
  { title: "Low volume, good ratio (6/4)", upvotes: 6, downvotes: 4 },
  { title: "Low volume, excellent ratio (9/1)", upvotes: 9, downvotes: 1 },
  { title: "High volume, excellent ratio (900/100)", upvotes: 900, downvotes: 100 },
  { title: "No votes yet", upvotes: 0, downvotes: 0 },
  { title: "Mixed votes (50/50)", upvotes: 50, downvotes: 50 }
])
