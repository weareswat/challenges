package pt.rubenmarques.service.post;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pt.rubenmarques.comparator.CustomPostComparator;
import pt.rubenmarques.domain.dtos.EntityMapper;
import pt.rubenmarques.domain.dtos.post.PostDTO;
import pt.rubenmarques.exception.PostNotFoundException;
import pt.rubenmarques.repository.post.PostRepository;
import pt.rubenmarques.service.post.interfaces.IPostService;
import pt.rubenmarques.util.VotingHelper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository repository;


    /**
     * Get all posts ordered by highest rating to lowest rating
     * @return List<PostDTO> posts
     */
    @Override
    public List<PostDTO> getPostsOrderedByRatingDESC() {
        var customPostComparator = new CustomPostComparator();
        return this.repository.findAll().stream()
                .map(EntityMapper::toDTO)
                .sorted(customPostComparator)
                .collect(Collectors.toList());
    }

    /**
     * Increment upVote on post by post id
     * @param id - post_id
     * @return post
     */
    @Override
    public PostDTO upVotePostById(final Long id) {
        var post = this.repository.findById(id)
                .orElseThrow(() -> { throw new PostNotFoundException(id); });

        post.setUpVotes(VotingHelper.incrementValue(post.getUpVotes()));

        post = this.repository.save(post);
        return EntityMapper.toDTO(post);
    }

    /**
     * Increment downVote on post by post id
     * @param id - post_id
     * @return post
     */
    @Override
    public PostDTO downVotePostById(final Long id) {
        var post = this.repository.findById(id)
                .orElseThrow(() -> { throw new PostNotFoundException(id); });

        post.setDownVotes(VotingHelper.decrementValue(post.getDownVotes()));

        post = this.repository.save(post);
        return EntityMapper.toDTO(post);
    }
}
