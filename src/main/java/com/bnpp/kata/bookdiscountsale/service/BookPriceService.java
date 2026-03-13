package com.bnpp.kata.bookdiscountsale.service;

import org.springframework.stereotype.Service;

@Service
public class BookPriceService {

    public double calculateBookPrice(String book, int quantity) {
        if(book==null|book.trim().isEmpty()|quantity<=0){
            return 0.0;
        }
        return 50.0 * quantity;
    }
}
