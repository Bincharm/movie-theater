package com.jpmc.theater.discount;

import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public class SecondShowingOfTheDayDiscount implements Function<Showing, Double> {
    @Override
    public Double apply(Showing showing) {
        return showing.getSequenceOfTheDay() == 2 ? 2.0 : 0;
    }
}
