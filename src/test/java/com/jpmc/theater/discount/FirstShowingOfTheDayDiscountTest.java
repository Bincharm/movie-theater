package com.jpmc.theater.discount;

import com.jpmc.theater.TestMovieFactory;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FirstShowingOfTheDayDiscountTest {
    @Test
    void whenFirstShowingOfTheDay_shouldReturnDiscount() {
        //given
        double ticketPrice = 100;
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 1, LocalDateTime.now(), ticketPrice);
        //when
        double discountAmount = new FirstShowingOfTheDayDiscount().apply(showing);
        //then
        assertEquals(3, discountAmount);

    }

    @Test
    void whenNotFirstShowingOfTheDay_shouldReturnZeroDiscount() {
        //given
        double ticketPrice = 100;
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 2, LocalDateTime.now(), ticketPrice);
        //when
        double discountAmount = new FirstShowingOfTheDayDiscount().apply(showing);
        //then
        assertEquals(0, discountAmount);

    }
}