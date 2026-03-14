package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.bnpp.kata.bookdiscountsale.constants.Constants.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookPriceServiceTests {

    private BookPriceService bookPriceService;

    private BookItems bookItems;
    private List<BookItems> bookItemsList;

    @BeforeEach
    public void setup() {
        bookPriceService = new BookPriceService();
        bookItemsList = new ArrayList<>();

    }

    @Test
    public void calculateSingleBookPrice() {


        bookItemsList = List.of(new BookItems("Clean code", 2));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(100.0, price);
    }

    @Test
    public void calculateSingleBookWithNullValue() {

        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(ZERO, price);
    }

    @Test
    public void calculateSingleBookWithZeroQuantity() {
        bookItemsList = List.of(new BookItems("Clean code", 0));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(ZERO, price);
    }

    @Test
    public void calculateTwoDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(95.0, price);
    }

    @Test
    public void calculateThreeDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(135.0, price);
    }

    @Test
    public void calculateFourDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1),
                new BookItems("Test Driven Development by Example", 1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(160.0, price);
    }

    @Test
    public void calculateFiveDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1),
                new BookItems("Test Driven Development by Example", 1),
                new BookItems("Working Effectively With Legacy Code",1));
        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(187.5, price);
    }

    @Test
    void calculatethreeBooksTwoExtraCopyDiscountsUniqueOnly() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2),
                new BookItems("Clean Architecture", 1));

        double price = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(235.0, price);
    }

}
