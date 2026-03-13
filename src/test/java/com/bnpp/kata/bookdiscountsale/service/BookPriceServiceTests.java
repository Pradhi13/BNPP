package com.bnpp.kata.bookdiscountsale.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookPriceServiceTests {

    private BookPriceService bookPriceService;

    @BeforeEach
    public void setup(){
        bookPriceService=new BookPriceService();
    }

    @Test
    public void calculateSingleBookPrice(){
        double price = bookPriceService.calculateBookPrice("Clean Code");
        assertEquals(50.0,price);
    }
}
