package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import com.bnpp.kata.bookdiscountsale.model.OrderResponse;
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
    void singleBook() {
        List<BookItems> books = List.of(new BookItems("Clean Code", 1));
        OrderResponse response = bookPriceService.calculateBookPrice(books);
        assertEquals(50, response.getDiscountedPrice());
    }

    @Test
    public void calculateSingleBookWithNullValue() {

        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(ZERO, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateSingleBookWithZeroQuantity() {
        bookItemsList = List.of(new BookItems("Clean code", 0));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(ZERO, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateTwoDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(95.0, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateThreeDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(135.0, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateFourDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1),
                new BookItems("Test Driven Development by Example", 1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(160.0, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateFiveDifferentBookPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 1),
                new BookItems("The Clean Coder", 1),
                new BookItems("Clean Architecture", 1),
                new BookItems("Test Driven Development by Example", 1),
                new BookItems("Working Effectively With Legacy Code",1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(187.5, reponse.getDiscountedPrice());
    }

    @Test
    void calculateTwoBooksTwoExtraCopyDiscountsUniqueOnly() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2));

        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(190.0, reponse.getDiscountedPrice());
    }

    @Test
    void calculateThreeBooksTwoExtraCopyDiscountsUniqueOnly() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2),
                new BookItems("Clean Architecture", 1));

        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(230.0, reponse.getDiscountedPrice());
    }

    @Test
    void calculateFourBooksTwoExtraCopyDiscountsUniqueOnly() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2),
                new BookItems("Clean Architecture", 1),
                new BookItems("Test Driven Development by Example", 1));

        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(255.0, reponse.getDiscountedPrice());
    }

    @Test
    public void calculateActualPrice() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2),
                new BookItems("Clean Architecture", 2),
                new BookItems("Test Driven Development by Example", 1),
                new BookItems("Working Effectively With Legacy Code",1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(400, reponse.getTotalPrice());
    }

    @Test
    public void calculatePossibleCombination() {
        bookItemsList = List.of(new BookItems("Clean code", 2),
                new BookItems("The Clean Coder", 2),
                new BookItems("Clean Architecture", 2),
                new BookItems("Test Driven Development by Example", 1),
                new BookItems("Working Effectively With Legacy Code",1));
        OrderResponse reponse = bookPriceService.calculateBookPrice(bookItemsList);
        assertEquals(320.0, reponse.getDiscountedPrice());
    }

}
