package com.jpmc.theater.discount;

import com.jpmc.theater.TestMovieFactory;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SpecialMovieDiscountTest {
    @Test
    void whenSpecialMovie_shouldReturnDiscount() {
        //given
        double ticketPrice = 100;
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(true), 1, LocalDateTime.now(), ticketPrice);
        //when
        double discountAmount = new SpecialMovieDiscount().apply(showing);
        //then
        assertEquals(ticketPrice*.2, discountAmount);

    }

    @Test
    void whenNotFirstShowingOfTheDay_shouldReturnZeroDiscount() {
        //given
        double ticketPrice = 100;
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(false), 2, LocalDateTime.now(), ticketPrice);
        //when
        double discountAmount = new SpecialMovieDiscount().apply(showing);
        //then
        assertEquals(0, discountAmount);

    }
}