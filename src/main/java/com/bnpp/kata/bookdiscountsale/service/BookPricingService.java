package com.bnpp.kata.bookdiscountsale.service;

public class BookPricingService {

    public double calculateOrderDetails(String book, int quantity) {

        if (book == null || book.trim().isEmpty() || quantity <= 0) {
            return 0.0;
        }
        return 50.0 * quantity;
    }


}
