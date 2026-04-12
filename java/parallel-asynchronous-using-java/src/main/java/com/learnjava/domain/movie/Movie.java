package com.learnjava.domain.movie;

import java.util.List;

public record Movie(MovieInfo movieInfo, List<Review> reviewList) {

}