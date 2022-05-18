package pt.rubenmarques.domain.dtos;


import pt.rubenmarques.domain.dtos.post.PostDTO;
import pt.rubenmarques.domain.post.Post;
import pt.rubenmarques.util.VotingHelper;

import java.util.List;
import java.util.stream.Collectors;

public class EntityMapper {

    public static PostDTO toDTO(final Post post) {
        return PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .slug(post.getSlug())
                .content(post.getContent())
                .upVotes(post.getUpVotes())
                .downVotes(post.getDownVotes())
                .build();
    }

}

