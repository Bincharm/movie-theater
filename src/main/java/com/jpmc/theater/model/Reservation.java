package com.jpmc.theater.model;

public class Reservation {
    private final Customer customer;
    private final Showing showing;
    private final int audienceCount;
    private final double ticketFee;

    public Reservation(Customer customer, Showing showing, int audienceCount, double ticketFee) {
        this.customer = customer;
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.ticketFee = ticketFee;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public double getTicketFee() {
        return ticketFee;
    }

    public double getTotalFee() {
        return ticketFee * audienceCount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", showing=" + showing +
                ", audienceCount=" + audienceCount +
                ", ticketFee=" + ticketFee +
                ", totalFee=" + getTotalFee() +
                '}';
    }
}