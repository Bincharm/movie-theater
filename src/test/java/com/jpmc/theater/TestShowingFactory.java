package com.jpmc.theater;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import org.mockito.cglib.core.Local;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TestShowingFactory {
    public static Showing dummyShowing(int sequence) {
        var date = LocalDate.of(2022, 06, 1);
        var time = LocalTime.of(13, 15);
        return new Showing(new Movie("Test", Duration.ofMinutes(50), true), sequence, LocalDateTime.of(date, time), 100);
    }
}
