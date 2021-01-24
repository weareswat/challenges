package com.mixfar.posts.controllers;

import com.mixfar.posts.utils.Utils;
import com.mixfar.posts.utils.enums.ErrorCode;
import com.mixfar.posts.utils.response.DataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class MainController {

    @RequestMapping("/")
    public ResponseEntity<?> home(){
        return new ResponseEntity<>(new DataResponse<>(ErrorCode.OK, "Welcome!"), HttpStatus.OK);
    }
}
