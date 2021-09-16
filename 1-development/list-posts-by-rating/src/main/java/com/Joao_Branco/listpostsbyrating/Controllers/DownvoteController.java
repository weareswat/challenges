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
public class DownvoteController {

    @Autowired
    PostService postService;

    @PutMapping("/downvote/:{id}")
    public ResponseEntity<Map<String, String>> incrementDownvotes(@PathVariable int id) {
        postService.incrementDownVotes(id);


        Map<String, String> map = new HashMap<>();
        map.put("message", "Downvotes incremented succesfully.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
