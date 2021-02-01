class Post
  class UpvoteCreateService
    def self.call(post)
      post.increment!(:upvote)
    end
  end
end
