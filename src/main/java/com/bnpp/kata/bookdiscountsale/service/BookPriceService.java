package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookPriceService {

    public double calculateBookPrice(BookItems bookItems) {

        if (bookItems.getTitle() == null | bookItems.getTitle().trim().isEmpty() | bookItems.getQuantity() <= 0) {

            return 0.0;
        } else {
            return 50.0 * bookItems.getQuantity();
        }
    }
}
