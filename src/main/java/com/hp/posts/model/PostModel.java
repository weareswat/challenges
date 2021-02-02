package com.hp.posts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "post_model")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private Integer votesUp;

    private Integer votesDown;

    private String votesUpPercentage;

    private String votesDownPercentage;
}
