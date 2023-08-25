class UpdateVotesByUser
  def initialize(post:, user:, value:, param:)
    @user = user
    @post = post
    @value = value
    @param = param
  end

  def call
    update_post if create_user_vote
  end

  private

  attr_reader :user, :post, :value, :param

  def create_user_vote
    UserVote.create(user_vote_params)
  end

  def user_vote_params
    {
      user_id: user.id,
      post_id: post.id
    }
  end

  def update_post
    post.send("#{param}=", value + 1)
    post.save
  end
end
