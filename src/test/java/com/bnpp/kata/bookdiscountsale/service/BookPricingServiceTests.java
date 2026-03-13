package com.bnpp.kata.bookdiscountsale.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BookPricingServiceTests {

    private BookPricingService bookPricingService;

    @BeforeEach
    public void setup() {
        bookPricingService = new BookPricingService();
    }

    @Test
    @DisplayName("Single book price should be 50")
    public void calculatePrice() {
        double price = bookPricingService.calculateOrderDetails("Clean Code",2);
        assertEquals(100.0, price);
    }

}
