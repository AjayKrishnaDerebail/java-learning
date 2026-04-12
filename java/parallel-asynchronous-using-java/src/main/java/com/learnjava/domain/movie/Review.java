package com.learnjava.domain.movie;


//@Min(value = 0L, message = "rating.negative : rating is negative and please pass a non-negative value")
public record Review(String reviewId,
                     Long movieInfoId,
                     String comment,
                     Double rating) {

}