package com.Joao_Branco.listpostsbyrating.repositories;

import com.Joao_Branco.listpostsbyrating.Exceptions.NewPostException;
import com.Joao_Branco.listpostsbyrating.domain.Post;
import com.Joao_Branco.listpostsbyrating.domain.PostRowMapper;
import com.Joao_Branco.listpostsbyrating.domain.PostSetExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Collection;

@Repository
public class PostRepositoryImpl implements PostRepository{

    private static final String SQL_CREATE = "INSERT INTO posts(post_id, post_body, upvotes, downvotes, total_votes) VALUES(NEXTVAL('posts_seq'), ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID = "SELECT post_id, upvotes, downvotes, post_body, total_votes FROM posts WHERE post_id = ?";
    private static final String SQL_GET_ALL_POSTS_ORDERED = "SELECT * FROM posts ORDER BY COALESCE(upvotes / NULLIF(total_votes,0), 0) DESC, COALESCE(downvotes / NULLIF(total_votes,0), 0) ASC, total_votes DESC";
    private static final String SQL_UPDATE_UPVOTES = "UPDATE posts SET upvotes = ?, total_votes = ? WHERE post_id = ?";
    private static final String SQL_UPDATE_DOWNVOTES = "UPDATE posts SET downvotes = ?, total_votes = ? WHERE post_id = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(int upvotes, int downvotes, String postBody) {

        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, postBody);
                ps.setInt(2, upvotes);
                ps.setInt(3, downvotes);
                ps.setInt(4, upvotes + downvotes);

                return ps;
            }, keyHolder);

            return (Integer) keyHolder.getKeys().get("post_id");
        } catch (Exception e) {

            System.out.println(e.toString());
            throw new NewPostException("Invalid arguments. Failed to create post.");
        }
    }

    @Override
    public Post findByPostId(int postId) {
        return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, postRowMapper, postId);
    }

    @Override
    public void incrementUpvotes(int postId) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, postRowMapper, postId);
            post.incrementUpvotes();
            post.incrementTotalVotes();

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_UPVOTES, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, post.getUpvotes());
                ps.setInt(2, post.getTotalVotes());
                ps.setInt(3, postId);

                return ps;
            }, keyHolder);

        } catch (Exception e) {

            System.out.println(e.toString());
            throw new NewPostException("Invalid arguments. Failed to increment Upvotes from post " + postId);
        }
    }

    @Override
    public void incrementDownVotes(int postId) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, postRowMapper, postId);
            post.incrementDownvotes();
            post.incrementTotalVotes();

            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_DOWNVOTES, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, post.getDownvotes());
                ps.setInt(2, post.getTotalVotes());
                ps.setInt(3, postId);

                return ps;
            }, keyHolder);

        } catch (Exception e) {

            System.out.println(e.toString());
            throw new NewPostException("Invalid arguments. Failed to increment Downvotes from post " + postId);
        }
    }

    @Override
    public Collection<Post> allPostsOrdered() {
        return jdbcTemplate.query(SQL_GET_ALL_POSTS_ORDERED, postSetExtractor);
    }

    private RowMapper<Post> postRowMapper = new PostRowMapper();
    private ResultSetExtractor<Collection<Post>> postSetExtractor = new PostSetExtractor();
}
