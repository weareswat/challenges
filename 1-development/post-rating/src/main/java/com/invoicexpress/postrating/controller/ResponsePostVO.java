package com.invoicexpress.postrating.controller;

import com.invoicexpress.postrating.service.ScorePostDTO;
import java.util.List;

public class ResponsePostVO extends ResponseTextVO {
    List<ScorePostDTO> listOfPosts;


    public List<ScorePostDTO> getListOfPosts() {
        return listOfPosts;
    }

    public void setListOfPosts(List<ScorePostDTO> listOfPosts) {
        this.listOfPosts = listOfPosts;
    }
}
