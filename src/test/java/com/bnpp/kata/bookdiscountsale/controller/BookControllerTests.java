package com.bnpp.kata.bookdiscountsale.controller;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import com.bnpp.kata.bookdiscountsale.model.Books;
import com.bnpp.kata.bookdiscountsale.service.BookPriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.springframework.http.MediaType;

import java.util.List;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookPriceService bookPricingService;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void shouldReturnBasketPrice() throws Exception {

        Books request = new Books();

        request.setBooks(List.of(
                new BookItems("Clean Code",2),
                new BookItems("Clean Coder",2),
                new BookItems("Clean Architecture",2),
                new BookItems("TDD",1),
                new BookItems("Legacy Code",1)));


        Mockito.when(bookPricingService.calculateBookPrice(Mockito.anyList()))
                .thenReturn(320.0);

        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("320.0"));
    }
}
