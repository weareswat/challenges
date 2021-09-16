package com.Joao_Branco.listpostsbyrating.domain;

import com.Joao_Branco.listpostsbyrating.Exceptions.InvalidVotesNumberException;

public class Post {

    private int postId;
    private int upvotes;
    private int downvotes;
    private int totalVotes;
    private String postBody;

    public Post(int postId, int upvotes, int downvotes, String postBody) {
        this.postId = postId;
        this.setUpvotes(upvotes);
        this.setDownvotes(downvotes);
        this.setTotalVotes(upvotes + downvotes);
        this.setPostBody(postBody);
    }

    public Post() {

    }

    public int getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(int totalVotes) {
        if (totalVotes >= 0)
            this.totalVotes = totalVotes;
        else
            throw new InvalidVotesNumberException("The number of total votes given is not valid. Number of Total Votes: " + totalVotes, totalVotes);
    }

    public void incrementTotalVotes() { this.totalVotes +=1;}

    public int getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        if (upvotes >= 0)
            this.upvotes = upvotes;
        else
            throw new InvalidVotesNumberException("The number of upvotes given is not valid. Number of Upvotes: " + upvotes, upvotes);
    }

    public void incrementUpvotes() { this.upvotes +=1;}

    public int getDownvotes() {
        return downvotes;
    }

    public void setDownvotes(int downvotes) {
        if (downvotes >= 0)
            this.downvotes = downvotes;
        else
            throw new InvalidVotesNumberException("The number of downvotes given is not valid. Number of Downvotes: " + downvotes, downvotes);
    }

    public void incrementDownvotes() { this.downvotes +=1;}

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {

        this.postBody = postBody;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", upvotes=" + upvotes +
                ", downvotes=" + downvotes +
                ", totalVotes=" + totalVotes +
                ", postBody='" + postBody + '\'' +
                '}';
    }
}
