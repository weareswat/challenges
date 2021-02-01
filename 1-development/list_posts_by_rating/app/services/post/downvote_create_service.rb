class Post
  class DownvoteCreateService
    def self.call(post)
      post.increment!(:downvote)
    end
  end
end
