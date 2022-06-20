package com.jpmc.theater;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @ParameterizedTest
    @MethodSource("negativeOrZeroTicketCount")
    void shouldThrowException_whenTicketCountIsLessThen1(int count) {
      IllegalStateException exception = assertThrows(IllegalStateException.class, () -> Validator.validateTicketCount(count));
      assertTrue(exception.getMessage().contains("Ticket count should be greater than 0"));
    }

    private static Stream<Arguments> negativeOrZeroTicketCount() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-1),
                Arguments.of(-4)
        );
    }

    @ParameterizedTest
    @MethodSource("moreThanZeroTicketCount")
    void whenMoreThanZeroTicketCount_shouldNotThrowException(int count) {
      assertDoesNotThrow(() -> Validator.validateTicketCount(count));
    }

    private static Stream<Arguments> moreThanZeroTicketCount() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(5),
                Arguments.of(100)
        );
    }
}