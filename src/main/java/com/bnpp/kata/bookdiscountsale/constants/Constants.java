package com.bnpp.kata.bookdiscountsale.constants;

import java.util.Map;

public class Constants {

    private Constants(){

    }
    public static final double ZERO = 0.0;
    public static final double BASE_PRICE = 50.0;
    public static final Map<Integer, Double> DISCOUNT = Map.of(
            1, 0.0,
            2, 0.05,
            3, 0.10,
            4, 0.20,
            5, 0.25
    );

}
