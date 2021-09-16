package com.Joao_Branco.listpostsbyrating.Controllers;

import com.Joao_Branco.listpostsbyrating.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UpvoteController {

    @Autowired
    PostService postService;

    @PutMapping("/upvote/:{id}")
    public ResponseEntity<Map<String, String>> incrementUpvotes(@PathVariable int id) {
        postService.incrementUpvotes(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Upvotes incremented succesfully.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
