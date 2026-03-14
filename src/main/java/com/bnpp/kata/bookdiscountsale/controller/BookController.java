package com.bnpp.kata.bookdiscountsale.controller;

import com.bnpp.kata.bookdiscountsale.model.Books;
import com.bnpp.kata.bookdiscountsale.model.OrderResponse;
import com.bnpp.kata.bookdiscountsale.service.BookPriceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookPriceService bookPriceService;

    public BookController(BookPriceService bookPriceService) {
        this.bookPriceService = bookPriceService;
    }

    @PostMapping("/calculatePrice")
    public OrderResponse totalOrderDetails(@RequestBody Books books) {
        return bookPriceService.calculateBookPrice(books.getBooks());

    }




}
