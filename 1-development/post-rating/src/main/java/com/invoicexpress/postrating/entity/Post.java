package com.invoicexpress.postrating.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Post")
public class Post {

    @Id
    @GeneratedValue
    private int id;
    private int upVotes;
    private int downVotes;

    public Post(){}
    public Post(int id, int upVotes, int downVotes) {
        this.id = id;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
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
}
