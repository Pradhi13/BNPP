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

        Map<String,Long> titleCounts = bookItemsList.stream().collect(Collectors.groupingBy(BookItems::getTitle,Collectors.counting()));

        long uniqueBooks = titleCounts.size();
        double discountSetPrice = uniqueBooks * BASE_PRICE * (1-DISCOUNTS[(int) uniqueBooks]);

        long totalQuantity = bookItemsList.stream()
                .mapToLong(BookItems::getQuantity).sum();
        double extraCopies = (totalQuantity-uniqueBooks)*BASE_PRICE;
        if(totalQuantity<=0){
            return 0.0;
        }
        return discountSetPrice + extraCopies;
    }
}
