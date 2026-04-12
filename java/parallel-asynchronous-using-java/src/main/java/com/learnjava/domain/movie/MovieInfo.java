package com.learnjava.domain.movie;

import java.time.LocalDate;
import java.util.List;

public record MovieInfo(String movieInfoId,
                        String name,
                        Integer year,
                        List<String> cast,
                        LocalDate release_date) {

}