package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bnpp.kata.bookdiscountsale.constants.Constants.*;

@Service
@Validated
public class BookPriceService {


    public double calculateBookPrice(@NotEmpty(message = "Book list cannot be empty")
                                     List<@Valid BookItems> bookItemsList) {

        Map<String,Long> titleCounts = bookItemsList.stream().collect(Collectors.groupingBy(BookItems::getTitle,Collectors.counting()));

        long uniqueBooks = titleCounts.size();
        double discountSetPrice = uniqueBooks * BASE_PRICE * (1-DISCOUNTS[(int) uniqueBooks]);

        long totalQuantity = bookItemsList.stream()
                .mapToLong(BookItems::getQuantity).sum();
        double extraCopies = (totalQuantity-uniqueBooks)*BASE_PRICE;
        if(totalQuantity<=0){
            return ZERO;
        }
        return discountSetPrice + extraCopies;
    }
}
