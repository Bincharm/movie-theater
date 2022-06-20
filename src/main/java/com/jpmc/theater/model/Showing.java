package com.jpmc.theater.model;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;
    private final double ticketPrice;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime, double ticketPrice) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.ticketPrice = ticketPrice;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

}
