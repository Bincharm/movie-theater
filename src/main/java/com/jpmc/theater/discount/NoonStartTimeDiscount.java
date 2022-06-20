package com.jpmc.theater.discount;

import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public class NoonStartTimeDiscount implements Function<Showing, Double> {
    @Override
    public Double apply(Showing showing) {
        int hour = showing.getStartTime().getHour();
        int min = showing.getStartTime().getMinute();
        if ((hour >= 11 && hour <= 15)  || (hour == 16 && min == 0))
            return showing.getTicketPrice() * 0.25;
        return 0.0;
    }
}
