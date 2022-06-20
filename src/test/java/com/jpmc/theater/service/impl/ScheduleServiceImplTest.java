package com.jpmc.theater.service.impl;

import com.jpmc.theater.TestShowingFactory;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.printer.FormatPrinter;
import com.jpmc.theater.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.verify;

class ScheduleServiceImplTest {
    Map<Integer, Showing> map;
    ScheduleService scheduleService;

    @BeforeEach
    void setup() {
        map = new HashMap<>();
        scheduleService = new ScheduleServiceImpl(map);
    }

    @Test
    void shouldAddToSchedule() {
        int sequence = 1;
        scheduleService.addToSchedule(sequence, TestShowingFactory.dummyShowing(sequence));

        assertEquals(1, map.size());
    }

    @Test
    void shouldThrowException_whenSequenceIsTaken() {
        int sequence = 1;
        scheduleService.addToSchedule(sequence, TestShowingFactory.dummyShowing(sequence));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> scheduleService.addToSchedule(sequence, TestShowingFactory.dummyShowing(sequence)));

        assertTrue(exception.getMessage().contains("Sequence is not available, please try another one"));
    }

    @Test
    void shouldReturnExistingShowing() {
        int sequence = 1;
        scheduleService.addToSchedule(sequence, TestShowingFactory.dummyShowing(sequence));

        assertEquals(1, map.size());

        Showing showing = scheduleService.getShowing(sequence);
        assertEquals(sequence, showing.getSequenceOfTheDay());


    }

    @Test
    void shouldThrowException_whenShowingIsNotPresent() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> scheduleService.getShowing(1));

        assertTrue(exception.getMessage().contains("Not able to find any showing for given sequence " + 1));
    }

    @Test
    void shouldPrint (){
        FormatPrinter formatPrinter = Mockito.mock(FormatPrinter.class);
        scheduleService.printSchedule(formatPrinter);
        verify(formatPrinter).print(any());

    }
}