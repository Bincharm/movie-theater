package com.jpmc.theater.discount;

import com.jpmc.theater.TestMovieFactory;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class SeventhOfTheMonthDiscountTest {
    @Test
    void whenSeventhOfTheMonth_shouldReturnDiscount() {
        //given
        double ticketPrice = 100;
        LocalDate someDate = LocalDate.of(2022, 6, 7);
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 1, LocalDateTime.of(someDate, LocalTime.now()), ticketPrice);
        //when
        double discountAmount = new SeventhOfTheMonthDiscount().apply(showing);
        //then
        assertEquals(1, discountAmount);

    }

    @Test
    void whenNotSeventhOfTheMonth_shouldReturnZeroDiscount() {
        //given
        double ticketPrice = 100;
        LocalDate someDate = LocalDate.of(2022, 6, 5);
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 2, LocalDateTime.of(someDate, LocalTime.now()), ticketPrice);
        //when
        double discountAmount = new SeventhOfTheMonthDiscount().apply(showing);
        //then
        assertEquals(0, discountAmount);

    }
}