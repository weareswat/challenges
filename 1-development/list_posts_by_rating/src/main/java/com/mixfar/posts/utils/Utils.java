package com.mixfar.posts.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Utils {
    public static long getDaysUntilNow(LocalDate date){
        return date.until(LocalDate.now(), ChronoUnit.DAYS);
    }

    public static float calculateRating(Long upvotes, Long downvotes, long days){
        Long totalVotes = upvotes + downvotes;
        float upvotesPercent = ((float) upvotes / totalVotes);
        float downvotesPercent = ((float) downvotes / totalVotes);

        days = days == 0L ? 1L : days;
        float upvotesByDay = ((float) upvotes / days);
        float downvotesByDay = ((float) downvotes / days);

        return (upvotesPercent * upvotesByDay) - (downvotesPercent * downvotesByDay);
    }
}
