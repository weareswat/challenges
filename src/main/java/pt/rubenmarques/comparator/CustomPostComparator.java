package pt.rubenmarques.comparator;

import lombok.AllArgsConstructor;
import pt.rubenmarques.domain.dtos.post.PostDTO;
import pt.rubenmarques.util.VotingHelper;

import java.util.Comparator;

@AllArgsConstructor
public class CustomPostComparator implements Comparator<PostDTO> {

    public int compare(PostDTO post1, PostDTO post2) {
        final var upVotePercentage1 = VotingHelper.getVotePercentage(post1.getUpVotes(), post1.getUpVotes() + post1.getDownVotes());
        final var upVotePercentage2 = VotingHelper.getVotePercentage(post2.getUpVotes(), post2.getUpVotes() + post2.getDownVotes());

        // Compare by % first then compare with amount of votes
        int percentageComparison = upVotePercentage2.compareTo(upVotePercentage1);
        if (percentageComparison != 0) return percentageComparison;

        return post2.getUpVotes().compareTo(post1.getUpVotes());
    }

}
