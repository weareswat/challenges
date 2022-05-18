package pt.rubenmarques.service.post.interfaces;

import pt.rubenmarques.domain.dtos.post.PostDTO;

import java.util.List;

public interface IPostService {

    List<PostDTO> getPostsOrderedByRatingDESC();

    PostDTO upVotePostById(final Long id);

    PostDTO downVotePostById(final Long id);
}
