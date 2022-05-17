package com.gmail.etpr99.jose.listpostsbyrating.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 * Represents a post. A post can have upvotes and downvotes.
 */
@Entity
public class Post {

    /**
     * The ID of this post.
     */
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The upvotes of this post.
     */
    @Column(name = "upvotes", nullable = false)
    private Long upvotes;

    /**
     * The downvotes of this post.
     */
    @Column(name = "downvotes", nullable = false)
    private Long downvotes;

    /**
     * Constructs a new {@link Post}.
     */
    public Post() {

    }

    /**
     * Constructs a new {@link Post} with the given ID.
     *
     * @param id The ID of this post.
     */
    public Post(Long id) {
        this.id = id;
        this.upvotes = 0L;
        this.downvotes = 0L;
    }

    /**
     * Constructs a new {@link Post} with the given ID, upvotes and downvotes.
     *
     * @param id The ID of this post.
     * @param upvotes The upvotes of this post.
     * @param downvotes The downvotes of this post.
     */
    public Post(Long id, Long upvotes, Long downvotes) {
        this.id = id;
        this.upvotes = upvotes;
        this.downvotes = downvotes;
    }

    /**
     * Gets id id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the upvotes.
     *
     * @return the upvotes
     */
    public Long getUpvotes() {
        return upvotes;
    }

    /**
     * Sets the upvotes.
     *
     * @param upvotes the upvotes
     */
    public void setUpvotes(Long upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Gets the downvotes.
     *
     * @return the downvotes.
     */
    public Long getDownvotes() {
        return downvotes;
    }

    /**
     * Sets the downvotes.
     *
     * @param downvotes the downvotes.
     */
    public void setDownvotes(Long downvotes) {
        this.downvotes = downvotes;
    }

    /**
     * Gets the upvote percentage.
     *
     * @return the upvote percentage
     */
    @Transient
    public Long getUpvotePercentage() {
        return upvotes * 100 / (upvotes + downvotes);
    }

    /**
     * Gets downvote percentage.
     *
     * @return the downvote percentage
     */
    @Transient
    public Long getDownvotePercentage() {
        return downvotes * 100 / (upvotes + downvotes);
    }
}
