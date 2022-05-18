package pt.rubenmarques.util;

public class VotingHelper {

    public static Long incrementValue(final Long value) {
        return value + 1;
    }

    public static Long decrementValue(final Long value) {
        return value == 0 ? 0 : value - 1;
    }

    public static Long getVotePercentage(final Long voteCount, final Long votesTotalCount) {
        return voteCount * 100 / votesTotalCount;
    }
}
