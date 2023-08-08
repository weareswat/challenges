class PostListService
  
  def by_rating
    @posts = Post.joins(:votes).select("posts.id, posts.title, SUM(CASE WHEN votes.kind IN (0) THEN 1 ELSE 0 END) as up_votes, SUM(CASE WHEN votes.kind IN (1) THEN 1 ELSE 0 END) as down_votes").group("posts.id")

    result =  @posts.map do |v|
      up_votes = v[:up_votes]
      down_votes =  v[:down_votes]
      total_votes = up_votes + down_votes
      rating = (up_votes.to_f / total_votes.to_f) * 100
      
      { id: v[:id], title: v[:title], rating: "#{rating}%", up_votes: up_votes, down_votes: down_votes}
    end
    result.sort_by{|e| -e[:rating]}
  end
  
end