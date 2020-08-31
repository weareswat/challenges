package com.ix.challenge.topic;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Post {

    @Id
    private Integer id;

    private Integer upVotes;

    private Integer downVotes;

    private Integer rating;

    public Post() {
        super();
    }

    public Post(int id, int upVotes, int downVotes) {
        super();
        this.id = id;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.rating = upVotes - downVotes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

