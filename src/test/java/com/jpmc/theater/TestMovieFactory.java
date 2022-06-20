package com.jpmc.theater;

import com.jpmc.theater.model.Movie;

import java.time.Duration;

public class TestMovieFactory {

    public static Movie getDummyMovie() {
        return new Movie("Avengers", Duration.ofMinutes(143L), false);
    }
    public static Movie getDummyMovie(boolean isSpecial) {
        return new Movie("Avengers", Duration.ofMinutes(143L), isSpecial);
    }
}
