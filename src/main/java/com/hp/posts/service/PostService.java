package com.hp.posts.service;

import org.springframework.stereotype.Service;

@Service
public class PostService {
    private double calculateWeight(Integer votes, Integer votesTotal) {
        return (double) votes / votesTotal;
    }

    public double calculateWeightPercentage(Integer votes, Integer votesTotal){
        double weight = this.calculateWeight(votes, votesTotal);

        return weight * 100;
    }
}
