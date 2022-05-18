package com.gmail.etpr99.jose.listpostsbyrating.services;

import com.gmail.etpr99.jose.listpostsbyrating.exceptions.PostNotFoundException;
import com.gmail.etpr99.jose.listpostsbyrating.models.Post;

import java.util.List;

/**
 * Defines the contract for business operations on {@link Post} objects.
 */
public interface PostService {

    /**
     * Gets all posts. The posts are sorted by the number of upvotes, descending, by default.
     *
     * @return A list of all posts.
     */
    List<Post> getAll();

    /**
     * Gets a post by its ID.
     *
     * @param id The ID of the post to get.
     * @return The post with the given ID.
     */
    Post getPost(Long id);

    /**
     * Gets a post upvotes by its ID.
     *
     * @param id The ID of the post to get.
     * @return The upvotes of the post with the given ID.
     */
    Long getPostUpvotes(Long id);

    /**
     * Adds an upvote to the given post.
     *
     * @param post The post to upvote.
     * @return The post with the upvote added.
     * @throws PostNotFoundException If the post does not exist.
     */
    Post upvotePost(Post post);

    /**
     * Adds an upvote to the post with the given ID.
     *
     * @param id The ID of the post to upvote.
     * @return The post with the upvote added.
     * @throws PostNotFoundException If the post with the given ID does not exist.
     */
    Post upvotePost(Long id);

    /**
     * Gets a post downvotes by its ID.
     *
     * @param id The ID of the post to get.
     * @return The downvotes of the post with the given ID.
     */
    Long getPostDownvotes(Long id);

    /**
     * Adds a downvote to the given post.
     *
     * @param post The post to downvote.
     * @return The post with the downvote added.
     * @throws PostNotFoundException If the post does not exist.
     */
    Post downvotePost(Post post);

    /**
     * Adds a downvote to the post with the given ID.
     *
     * @param id The ID of the post to downvote.
     * @return The post with the downvote added.
     * @throws PostNotFoundException If the post with the given ID does not exist.
     */
    Post downvotePost(Long id);

    /**
     * Inserts a new post.
     *
     * @param post The post to insert.
     * @return The inserted post.
     */
    Post insertPost(Post post);

    /**
     * Inserts a new list of posts.
     *
     * @param posts The list of posts to insert.
     * @return The list of inserted posts.
     */
    List<Post> insertPosts(List<Post> posts);

    /**
     * Updates an existing post.
     *
     * @param post The post to update.
     * @return The updated post.
     * @throws PostNotFoundException If the post does not exist.
     */
    Post updatePost(Post post);

    /**
     * Updates an existing list of posts.
     *
     * @param posts The list of posts to update.
     * @return The list of updated posts.
     * @throws PostNotFoundException If any of the posts do not exist.
     */
    List<Post> updatePosts(List<Post> posts);

    /**
     * Deletes a post.
     *
     * @param post The post to delete.
     * @throws PostNotFoundException If the post does not exist.
     */
    void deletePost(Post post);

    /**
     * Deletes a list of posts.
     *
     * @param posts The list of the posts to delete.
     * @throws PostNotFoundException If any of the posts do not exist.
     */
    void deletePosts(List<Post> posts);
}
