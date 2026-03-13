package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookPriceServiceTests {

    private BookPriceService bookPriceService;

    private BookItems bookItems;
    private List<BookItems> bookItemsList;
    @BeforeEach
    public void setup(){
        bookPriceService=new BookPriceService();
        bookItemsList = new ArrayList<>();

    }

    @Test
    public void calculateSingleBookPrice(){


        bookItemsList = List.of(new BookItems("Clean code",2));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(100.0,price);
    }

    @Test
    public void calculateSingleBookWithNullValue(){

        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(0.0,price);
    }

    @Test
    public void calculateSingleBookWithZeroQuantity(){
        bookItemsList = List.of(new BookItems("Clean code",0));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(0.0,price);
    }

    @Test
    public void calculateTwoDifferentBookPrice(){
        bookItemsList = List.of(new BookItems("Clean code",1),
                new BookItems("The Clean Coder",1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(100.0,price);
    }

    @Test
    public void calculateThreeDifferentBookPrice(){
        bookItemsList = List.of(new BookItems("Clean code",1),
                new BookItems("The Clean Coder",1),
                new BookItems("Clean Architecture",1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(150.0,price);
    }
}
