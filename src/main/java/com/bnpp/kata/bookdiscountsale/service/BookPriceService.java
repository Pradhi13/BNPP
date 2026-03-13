package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookPriceService {

    private static final double BASE_PRICE = 50.0;
    private static final double[] DISCOUNTS = {0.0, 0.0, 0.05, 0.10, 0.20, 0.25};

    public double calculateBookPrice(List<BookItems> bookItemsList) {
        if (bookItemsList == null || bookItemsList.isEmpty()) {

            return 0.0;
        }
        long uniqueBooks = bookItemsList.stream()
                .map(BookItems::getTitle)
                .collect(Collectors.toSet())
                .size();
        double totalBooks = bookItemsList.stream()
                .mapToDouble(BookItems::getQuantity)
                .sum();
        if(totalBooks<=0){
            return 0.0;
        }
        double discount = DISCOUNTS[(int) Math.min(uniqueBooks, DISCOUNTS.length - 1)];
        return totalBooks*BASE_PRICE*(1-discount);
    }
}
