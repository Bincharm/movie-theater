package com.jpmc.theater.discount;

import com.jpmc.theater.TestMovieFactory;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NoonStartTimeDiscountTest {
    @ParameterizedTest
    @MethodSource("provideTimesWithin11And16")
    void whenStartTimeBetween11And16_shouldReturnDiscount(LocalTime localTime) {
        //given
        double ticketPrice = 100;
        LocalDate someDate = LocalDate.of(2022, 6, 1);
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 5, LocalDateTime.of(someDate, localTime), ticketPrice);
        //when
        double discountAmount = new NoonStartTimeDiscount().apply(showing);
        //then
        assertEquals(ticketPrice*0.25, discountAmount);
    }

    private static Stream<Arguments> provideTimesWithin11And16() {
        return Stream.of(
                Arguments.of(LocalTime.of(15, 30)),
                Arguments.of(LocalTime.of(11, 0)),
                Arguments.of(LocalTime.of(16, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("provideTimesOutsideOf11And16")
    void whenStartTimeNotBetween11And16_shouldReturnZeroDiscount(LocalTime notNoonTime) {
        //given
        double ticketPrice = 100;
        LocalDate someDate = LocalDate.of(2022, 6, 1);
        Showing showing = new Showing(TestMovieFactory.getDummyMovie(), 5, LocalDateTime.of(someDate, notNoonTime), ticketPrice);
        //when
        double discountAmount = new NoonStartTimeDiscount().apply(showing);
        //then
        assertEquals(0, discountAmount);

    }
    private static Stream<Arguments> provideTimesOutsideOf11And16() {
        return Stream.of(
                Arguments.of(LocalTime.of(16, 30)),
                Arguments.of(LocalTime.of(10, 55)),
                Arguments.of(LocalTime.of(17, 0))
        );
    }

}