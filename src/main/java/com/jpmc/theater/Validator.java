package com.jpmc.theater;

public class Validator {
    public static void validateTicketCount(int ticketCount) {
        if (ticketCount <= 0) throw new IllegalStateException("Ticket count should be greater than 0");
    }
}
