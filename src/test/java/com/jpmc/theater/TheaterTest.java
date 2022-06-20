package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.printer.FormatPrinter;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TheaterTest {
    ScheduleService scheduleService;
    ReservationService reservationService;
    Theater theater;

    @BeforeEach
    void setup() {
        scheduleService = Mockito.mock(ScheduleService.class);
        reservationService = Mockito.mock(ReservationService.class);
        theater = new Theater(scheduleService, "test theater", reservationService);
    }

    @Test
    void shouldReserve() {
        var customer = new Customer("John", "ID");
        var showing = TestShowingFactory.dummyShowing(1);
        when(reservationService.reserve(anyObject(), anyInt(), anyInt()))
                .thenReturn(new Reservation(customer, showing, 1, 100));
        theater.reserve(customer, 1, 1);
        verify(reservationService).reserve(any(), anyInt(), anyInt());
    }

    @Test
    void shouldPrintSchedule() {
        FormatPrinter formatPrinter = Mockito.mock(FormatPrinter.class);
        theater.printSchedule(formatPrinter);
        verify(scheduleService).printSchedule(formatPrinter);
    }

}