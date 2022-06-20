package com.jpmc.theater.util;

import com.jpmc.theater.Validator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {
    @ParameterizedTest
    @MethodSource("negativeOrZeroTicketCount")
    void shouldPrintPluralHoursAndTime(Duration duration, String expectedString) {
        //when
        var actualString = Util.humanReadableFormat(duration);
        //then
        assertEquals(expectedString, actualString);
    }

    private static Stream<Arguments> negativeOrZeroTicketCount() {
        return Stream.of(
                Arguments.of(Duration.ofMinutes(143L), "(2 hours 23 minutes)"),
                Arguments.of(Duration.ofMinutes(60L), "(1 hour 0 minutes)"),
                Arguments.of(Duration.ofMinutes(1L), "(0 hours 1 minute)"),
                Arguments.of(Duration.ofMinutes(61L), "(1 hour 1 minute)"),
                Arguments.of(Duration.ofMinutes(115L), "(1 hour 55 minutes)")
        );
    }
}