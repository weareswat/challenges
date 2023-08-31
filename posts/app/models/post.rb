class Post < ApplicationRecord
  belongs_to :user

  def ratio
    total = self.upvotes + self.downvotes
    return 50.0 if total.zero?

    (self.upvotes.to_f / total.to_f * 100).round(2)
  end

  def self.sorted(query = nil)
    posts = query.nil? ? Post.all : where(query)

    # Notice that each comparison puts b before a.
    # This is important to make sure the posts are sorted in desc order
    posts.sort {|a,b| a.ratio == b.ratio ? b.upvotes <=> a.upvotes : b.ratio <=> a.ratio}
  end
end
