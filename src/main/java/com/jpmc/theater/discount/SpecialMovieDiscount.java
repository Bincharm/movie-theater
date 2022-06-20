package com.jpmc.theater.discount;

import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public class SpecialMovieDiscount implements Function<Showing, Double> {
    @Override
    public Double apply(Showing showing) {
        return showing.getMovie().isSpecial() ? showing.getTicketPrice() * 0.2 : 0;
    }
}
