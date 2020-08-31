package com.ix.challenge.topic;

import java.util.List;

public interface PostService {

    public List<Post> getAll();

    public Post savePost(Post post);

    public Post upVote(int postId);

    public Post downVote(int postId);

}
