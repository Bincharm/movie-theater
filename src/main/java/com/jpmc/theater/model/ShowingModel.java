package com.jpmc.theater.model;

import java.time.LocalDateTime;

public class ShowingModel {
    private final String movieTitle;
    private final int sequenceOfTheDay;
    private final String showStartTime;
    private final String duration;
    private final double ticketPrice;

    public ShowingModel(String movieTitle, int sequenceOfTheDay, String showStartTime, String duration, double ticketPrice) {
        this.movieTitle = movieTitle;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public String getShowStartTime() {
        return showStartTime;
    }

    public String getDuration() {
        return duration;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }
}
