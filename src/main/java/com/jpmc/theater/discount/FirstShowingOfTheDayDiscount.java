package com.jpmc.theater.discount;

import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public class FirstShowingOfTheDayDiscount implements Function<Showing, Double> {
    @Override
    public Double apply(Showing showing) {
        return showing.getSequenceOfTheDay() == 1 ? 3.0 : 0;
    }
}
