package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static com.bnpp.kata.bookdiscountsale.constants.Constants.*;

@Service
@Validated
public class BookPriceService {


    private final Map<String, Double> cache = new HashMap<>();

    public double calculateBookPrice(@NotEmpty(message = "Book list cannot be empty")
                                     List<@Valid BookItems> bookItemsList) {
        int[] quantities = bookItemsList.stream().mapToInt(BookItems::getQuantity).toArray();
        return findMinPrice(quantities);
    }

    private double findMinPrice(int[] books) {

        String key = Arrays.toString(books);
        //[2,2,1]

        if (cache.containsKey(key))
            return cache.get(key);

        boolean empty = Arrays.stream(books).allMatch(q -> q == 0);
        if (empty)
            return ZERO;

        double minPrice = Double.MAX_VALUE;

        for (int size = 1; size <= books.length; size++) {

            int[] next = Arrays.copyOf(books, books.length);
            int count = 0;

            for (int i = 0; i < next.length && count < size; i++) {

                if (next[i] > 0) {
                    next[i]--;
                    count++;
                }
            }

            if (count == size) {

                double price = size * BASE_PRICE * (1 - DISCOUNT.get(size));

                minPrice = Math.min(minPrice, price + findMinPrice(next));
            }
        }

        cache.put(key, minPrice);

        return minPrice;
    }
}
