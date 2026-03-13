package com.bnpp.kata.bookdiscountsale.service;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookPriceService {

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
            return  0.0;
        }
        return uniqueBooks * totalBooks * 50.0;
    }
}
