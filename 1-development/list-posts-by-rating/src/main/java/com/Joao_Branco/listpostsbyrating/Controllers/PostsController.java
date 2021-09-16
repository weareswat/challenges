package com.Joao_Branco.listpostsbyrating.Controllers;


import com.Joao_Branco.listpostsbyrating.domain.Post;
import com.Joao_Branco.listpostsbyrating.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostsController {

    @Autowired
    PostService postService;

    @PostMapping("/addpost")
    public ResponseEntity<Map<String, String>> addPost(@RequestBody Map<String, Object> postMap) {
        Integer upvotes = (Integer) postMap.get("upvotes");
        Integer downvotes = (Integer) postMap.get("downvotes");
        String postBody = (String) postMap.get("postBody");
        postService.addPost(upvotes, downvotes, postBody);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Post added succesfully.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public Collection<Post> allPosts() {
        Collection<Post> posts = postService.allPostsOrdered();

        return posts;
    }
}
