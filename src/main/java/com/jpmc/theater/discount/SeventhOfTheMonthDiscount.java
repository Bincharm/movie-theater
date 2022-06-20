package com.jpmc.theater.discount;

import com.jpmc.theater.model.Showing;

import java.util.function.Function;

public class SeventhOfTheMonthDiscount implements Function<Showing, Double> {
    @Override
    public Double apply(Showing showing) {
        return showing.getStartTime().getDayOfMonth()==7 ? 1.0 : 0;
    }
}
