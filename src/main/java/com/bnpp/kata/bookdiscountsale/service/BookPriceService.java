package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.exception.InvalidBookException;
import com.bnpp.kata.bookdiscountsale.model.BookItems;
import com.bnpp.kata.bookdiscountsale.model.GroupDetails;
import com.bnpp.kata.bookdiscountsale.model.OrderResponse;
import com.bnpp.kata.bookdiscountsale.model.PriceResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.IntStream;

import static com.bnpp.kata.bookdiscountsale.constants.Constants.*;

@Service
@Validated
public class BookPriceService {


    private final Map<String, Double> cache = new HashMap<>();

    public OrderResponse calculateBookPrice(@NotEmpty(message = "Book list cannot be empty")
                                            List<@Valid BookItems> bookItemsList) {

        if (bookItemsList.stream().anyMatch(item ->
                item.getQuantity() <= 0 || item.getTitle().trim().isEmpty())) {
            throw new InvalidBookException("Invalid book items: check quantity/title");
        }
        int[] quantities = bookItemsList.stream()
                .mapToInt(BookItems::getQuantity)
                .toArray();

        String[] titles = bookItemsList.stream()
                .map(BookItems::getTitle)
                .toArray(String[]::new);

        PriceResult result = findBestPrice(quantities, titles);

        int totalBooks = Arrays.stream(quantities).sum();

        double totalPrice = totalBooks * BASE_PRICE;

        return new OrderResponse(
                result.getGroups(),
                totalPrice,
                result.getPrice()
        );
    }

    private PriceResult findBestPrice(int[] books, String[] titles) {

        if (Arrays.stream(books).allMatch(q -> q == 0)) {
            return new PriceResult(0.0, new ArrayList<>());
        }

        return IntStream.rangeClosed(1, books.length)
                .mapToObj(size -> calculateGroupPrice(size, books, titles))
                .filter(Objects::nonNull)
                .min(Comparator.comparingDouble(PriceResult::getPrice))
                .orElse(new PriceResult(Double.MAX_VALUE, new ArrayList<>()));
    }

    private PriceResult calculateGroupPrice(int size, int[] books, String[] titles) {

        int[] next = Arrays.copyOf(books, books.length);

        List<Integer> selectedIndices = IntStream.range(0, next.length)
                .filter(i -> next[i] > 0)
                .limit(size)
                .boxed()
                .toList();
        if (selectedIndices.size() != size) {
            return null;
        }
        selectedIndices.forEach(i -> next[i]--);

        List<String> groupBooks = selectedIndices.stream().map(i -> titles[i]).toList();

        double groupPrice = size * BASE_PRICE * (1 - DISCOUNT.get(size));

        PriceResult result = findBestPrice(next, titles);

        double total = groupPrice + result.getPrice();

        List<GroupDetails> groups = new ArrayList<>();

        groups.add(new GroupDetails(groupBooks, size, DISCOUNT.get(size) * 100, groupPrice));

        groups.addAll(result.getGroups());

        return new PriceResult(total, groups);

    }
}
