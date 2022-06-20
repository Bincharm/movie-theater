package com.jpmc.theater.service.impl;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DiscountServiceImplTest {
    DiscountService discountService;
    Function<Showing, Double> discount1;
    Function<Showing, Double> discount2;
    Function<Showing, Double> discount3;
    Showing showing;

    @BeforeEach
    @SuppressWarnings("unchecked")
    void setup() {
        discount1 = (Function<Showing, Double>)Mockito.mock(Function.class);
        discount2 = (Function<Showing, Double>)Mockito.mock(Function.class);
        discount3 = (Function<Showing, Double>)Mockito.mock(Function.class);
        List<Function<Showing, Double>> discountRules = List.of(discount1, discount2, discount3);
        discountService = new DiscountServiceImpl(discountRules);
        showing = Mockito.mock(Showing.class);
    }

    @Test
    void getMaximumDiscountOfThree() {
        //given
        when(discount1.apply(any())).thenReturn(5.0);
        when(discount2.apply(any())).thenReturn(8.0);
        when(discount3.apply(any())).thenReturn(11.0);
        //when
        double discount = discountService.getDiscount(showing);
        //then
        assertEquals(11.0, discount);
        verify(discount1).apply(any());
        verify(discount2).apply(any());
        verify(discount3).apply(any());
    }
}