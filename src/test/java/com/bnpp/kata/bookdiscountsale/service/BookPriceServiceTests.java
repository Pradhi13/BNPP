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
        double price = bookPriceService.calculateBookPrice("Clean Code",2);
        assertEquals(100.0,price);
    }

    @Test
    public void calculateSingleBookWithEmptyData(){
        double price = bookPriceService.calculateBookPrice("",2);
        assertEquals(0.0,price);
    }

    @Test
    public void calculateSingleBookWithZeroQuantity(){
        double price = bookPriceService.calculateBookPrice("Clean Code",0);
        assertEquals(0.0,price);
    }
}
