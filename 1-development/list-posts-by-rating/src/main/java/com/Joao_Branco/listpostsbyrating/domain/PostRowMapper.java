package com.Joao_Branco.listpostsbyrating.domain;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {

    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostId(rs.getInt("post_id"));
        post.setPostBody(rs.getString("post_body"));
        post.setUpvotes(rs.getInt("upvotes"));
        post.setDownvotes(rs.getInt("downvotes"));
        post.setTotalVotes(rs.getInt("total_votes"));

        return post;
    }
}
