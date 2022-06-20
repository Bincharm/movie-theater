package com.jpmc.theater.service;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.Reservation;

public interface ReservationService {

    Reservation reserve(Customer customer, int sequence, int howManyTickets);

}
