package com.jpmc.theater.model;

import java.time.Duration;
import java.util.Objects;

public class Movie {

    private final boolean isSpecial;

    private final String title;
    private String description;
    private final Duration runningTime;


    public Movie(String title, Duration runningTime, boolean isSpecial) {
        this.title = title;
        this.runningTime = runningTime;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public boolean isSpecial() {
        return isSpecial;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecial, movie.isSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, isSpecial);
    }
}