package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.DiscountService;
import com.jpmc.theater.service.ReservationService;
import com.jpmc.theater.service.ScheduleService;

public class ReservationServiceImpl implements ReservationService {

    private final ScheduleService scheduleService;
    private final DiscountService discountService;

    public ReservationServiceImpl(ScheduleService scheduleService, DiscountService discountService) {
        this.scheduleService = scheduleService;
        this.discountService = discountService;
    }

    @Override
    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing = scheduleService.getShowing(sequence);
        double discount = discountService.getDiscount(showing);
        double ticketFee = showing.getTicketPrice() - discount;
        ticketFee = ticketFee < 0 ? 0 : ticketFee;
        return new Reservation(customer, showing, howManyTickets, ticketFee);
    }
}
