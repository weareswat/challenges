package com.mixfar.posts.model;

import com.mixfar.posts.utils.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "post")
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_body")
    private String postBody;

    @Column(name = "up_votes")
    private Long upVotes;

    @Column(name = "down_votes")
    private Long downVotes;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "date_time")
    private LocalDate publishDate;

    public Post() {
    }

    public Post(Long postId, String postTitle, String postBody, Long upVotes, Long downVotes, LocalDate publishDate) {
        this.postId = postId;
        this.postTitle = postTitle;
        this.postBody = postBody;
        this.upVotes = upVotes;
        this.downVotes = downVotes;
        this.rating = Utils.calculateRating(upVotes, downVotes, Utils.getDaysUntilNow(publishDate));
        this.publishDate = publishDate;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public Long getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Long upVotes) {
        this.upVotes = upVotes;
    }

    public Long getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(Long downVotes) {
        this.downVotes = downVotes;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
