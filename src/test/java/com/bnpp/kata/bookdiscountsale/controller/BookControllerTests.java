package com.bnpp.kata.bookdiscountsale.controller;

import com.bnpp.kata.bookdiscountsale.model.BookItems;
import com.bnpp.kata.bookdiscountsale.model.Books;
import com.bnpp.kata.bookdiscountsale.model.GroupDetails;
import com.bnpp.kata.bookdiscountsale.model.OrderResponse;
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

import java.util.ArrayList;
import java.util.Arrays;
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


        OrderResponse response = getOrderResponse();
        Mockito.when(bookPricingService.calculateBookPrice(Mockito.anyList()))
                .thenReturn(response);

        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.groups").isArray())
                .andExpect(jsonPath("$.totalPrice").value(400.0))
                .andExpect(jsonPath("$.discountedPrice").value(320.0));
    }

    @Test
    void shouldReturnBadRequestForInvalidJson() throws Exception {

        mockMvc.perform(post("/api/books/calculatePrice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("invalid-json"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnUnsupportedMediaTypeWhenContentTypeMissing() throws Exception {

        mockMvc.perform(post("/api/books/calculatePrice")
                        .content("{\"books\":[1,2,3]}"))
                .andExpect(status().isUnsupportedMediaType());
    }

    private static OrderResponse getOrderResponse() {
        List<String> books1 = Arrays.asList("Clean Code","Clean Coder","Clean Architecture","TDD","Legacy Code");
        List<String> books2 = Arrays.asList("Clean Coder","Clean Architecture","TDD","Legacy Code");

        GroupDetails groupDetails1 = new GroupDetails(books1,4, 20.0, 160.0);
        GroupDetails groupDetails2 = new GroupDetails(books2,4, 20.0, 160.0);

        List<GroupDetails> groupDetailsList = new ArrayList<>();
        groupDetailsList.add(groupDetails1);
        groupDetailsList.add(groupDetails2);
        OrderResponse response = new OrderResponse(groupDetailsList,
                400.0,320.0
        );
        return response;
    }
}
