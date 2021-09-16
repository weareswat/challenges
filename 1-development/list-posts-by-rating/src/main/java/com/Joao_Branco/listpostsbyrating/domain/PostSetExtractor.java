package com.Joao_Branco.listpostsbyrating.domain;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostSetExtractor implements ResultSetExtractor<Collection<Post>> {
    @Override
    public Collection<Post> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Post> postList = new ArrayList<>();

        while(rs.next()){
            int postId = rs.getInt("post_id");
            int upvotes = rs.getInt("upvotes");
            int downvotes = rs.getInt("downvotes");
            String postBody = rs.getString("post_body");
            Post post = new Post(postId, upvotes, downvotes, postBody);
            postList.add(post);

        }

        return postList;
    }
}
