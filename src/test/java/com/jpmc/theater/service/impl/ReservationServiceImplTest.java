package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

class ReservationServiceImplTest {
    ReservationService reservationService;
    DiscountService discountService;
    ScheduleService scheduleService;
    Showing showing;

    @BeforeEach
    void setup() {
        discountService = Mockito.mock(DiscountService.class);
        scheduleService = Mockito.mock(ScheduleService.class);
        showing = Mockito.mock(Showing.class);
        reservationService = new ReservationServiceImpl(scheduleService, discountService);
    }

    @Test
    void shouldReserveForSingleCustomerWithNoDiscount() {
        //given
        when(discountService.getDiscount(any())).thenReturn(0.);
        when(scheduleService.getShowing(1)).thenReturn(showing);
        when(showing.getTicketPrice()).thenReturn(100.0);
        //when
        var reservation = reservationService.reserve(new Customer("test", "ID"), 1, 1);
        //then
        assertEquals(100, reservation.getTotalFee());
        assertEquals(100, reservation.getTicketFee());
        assertEquals(1, reservation.getAudienceCount());
        assertEquals("test", reservation.getCustomer().getName());
        assertEquals("ID", reservation.getCustomer().getId());
    }

    @Test
    void shouldReserveForFiveCustomersWithNoDiscount() {
        //given
        when(discountService.getDiscount(any())).thenReturn(0.);
        when(scheduleService.getShowing(1)).thenReturn(showing);
        when(showing.getTicketPrice()).thenReturn(100.0);
        //when
        var reservation = reservationService.reserve(new Customer("test", "ID"), 1, 5);
        //then
        assertEquals(500, reservation.getTotalFee());
        assertEquals(100, reservation.getTicketFee());
        assertEquals(5, reservation.getAudienceCount());
        assertEquals("test", reservation.getCustomer().getName());
        assertEquals("ID", reservation.getCustomer().getId());
    }

    @Test
    void shouldReserveForFiveCustomersWithDiscount() {
        //given
        when(discountService.getDiscount(any())).thenReturn(5.0);
        when(scheduleService.getShowing(1)).thenReturn(showing);
        when(showing.getTicketPrice()).thenReturn(100.0);
        //when
        var reservation = reservationService.reserve(new Customer("test", "ID"), 1, 5);
        //then
        assertEquals(475, reservation.getTotalFee());
        assertEquals(95, reservation.getTicketFee());
        assertEquals(5, reservation.getAudienceCount());
        assertEquals("test", reservation.getCustomer().getName());
        assertEquals("ID", reservation.getCustomer().getId());
    }

    @Test
    void totalPriceShouldBeZero_whenDiscountIsBiggerThanPrice() {
        //given
        when(discountService.getDiscount(any())).thenReturn(50.0);
        when(scheduleService.getShowing(1)).thenReturn(showing);
        when(showing.getTicketPrice()).thenReturn(45.0);
        //when
        var reservation = reservationService.reserve(new Customer("test", "ID"), 1, 5);
        //then
        assertEquals(0, reservation.getTotalFee());
        assertEquals(0, reservation.getTicketFee());
        assertEquals(5, reservation.getAudienceCount());
        assertEquals("test", reservation.getCustomer().getName());
        assertEquals("ID", reservation.getCustomer().getId());
    }


}