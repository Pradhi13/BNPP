package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.bnpp.kata.bookdiscountsale.constants.Constants.*;

@Service
@Validated
public class BookPriceService {


    private final Map<String, Double> cache = new HashMap<>();

    public double calculateBookPrice(@NotEmpty(message = "Book list cannot be empty")
                                     List<@Valid BookItems> bookItemsList) {
        int[] quantities = bookItemsList.stream().mapToInt(BookItems::getQuantity).toArray();
        return findBestPrice(quantities);
    }

    private double findBestPrice(int[] books) {

        String key = Arrays.toString(books);

        if (cache.containsKey(key))
            return cache.get(key);

        if (Arrays.stream(books).allMatch(q -> q == ZERO))
            return ZERO;

        double minPrice = IntStream.rangeClosed(1, books.length)
                .mapToDouble(size -> calculateGroupPrice(size, books))
                .filter(price -> price > ZERO)
                .min()
                .orElse(Double.MAX_VALUE);
        cache.put(key, minPrice);
        return minPrice;
    }
    private double calculateGroupPrice(int size, int[] books) {

        int[] next = Arrays.copyOf(books, books.length);

        int count = (int) java.util.stream.IntStream.range(0, next.length)
                .filter(i -> next[i] > ZERO)
                .limit(size)
                .peek(i -> next[i]--)
                .count();

        if (count != size) {
            return ZERO;
        }

        double price = size * BASE_PRICE * (1 - DISCOUNT.get(size));

        return price + findBestPrice(next);
    }
}
