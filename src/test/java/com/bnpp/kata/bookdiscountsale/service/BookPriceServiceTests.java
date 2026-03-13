package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookPriceServiceTests {

    private BookPriceService bookPriceService;

    private BookItems bookItems;

    @BeforeEach
    public void setup(){
        bookPriceService=new BookPriceService();
        bookItems = new BookItems();

    }

    @Test
    public void calculateSingleBookPrice(){
        bookItems.setTitle("Clean Code");
        bookItems.setQuantity(2);
        double price = bookPriceService.calculateBookPrice(bookItems);
        assertEquals(100.0,price);
    }

    @Test
    public void calculateSingleBookWithEmptyData(){
        bookItems.setTitle("");
        bookItems.setQuantity(2);
        double price = bookPriceService.calculateBookPrice(bookItems);
        assertEquals(0.0,price);
    }

    @Test
    public void calculateSingleBookWithZeroQuantity(){
        bookItems.setTitle("Clean Code");
        bookItems.setQuantity(0);
        double price = bookPriceService.calculateBookPrice(bookItems);
        assertEquals(0.0,price);
    }
}
