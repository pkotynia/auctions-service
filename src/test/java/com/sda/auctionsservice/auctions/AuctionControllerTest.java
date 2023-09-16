package com.sda.auctionsservice.auctions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuctionControllerTest {

    @Test
    void shouldAddAuctionToDb(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auctions")
                .bodyValue(new Auction("test auction", BigDecimal.ONE, BigDecimal.ONE, "test description", LocalDateTime.now()))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldCheckIfValidationIsWorking(@Autowired WebTestClient testClient) {
        testClient
                .post()
                .uri("/auctions")
                .bodyValue(new Auction("test auction", BigDecimal.valueOf(-1.0), BigDecimal.ONE, "test description", LocalDateTime.now()))
                .exchange()
                .expectStatus().isBadRequest();
    }


    @Test
    void shouldDeleteAuction(@Autowired WebTestClient testClient) {
        testClient
                .delete()
                .uri("/auctions/1")
                .exchange()
                .expectStatus().isNoContent();
    }
}