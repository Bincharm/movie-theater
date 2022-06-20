package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.DiscountService;

import java.util.List;
import java.util.function.Function;

public class DiscountServiceImpl implements DiscountService {

    private final List<Function<Showing, Double>> discountRules;

    public DiscountServiceImpl(List<Function<Showing, Double>> discountRules) {
        this.discountRules = discountRules;
    }

    @Override
    public double getDiscount(Showing showing) {
        double maxDiscount = 0;

        for(var rule : discountRules){
            maxDiscount = Math.max(maxDiscount, rule.apply(showing));
        }
        return maxDiscount;
    }

}
