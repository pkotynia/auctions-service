package com.sda.auctionsservice.auctions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SellerRepositoryTest {

    @Autowired
    private SellerRepository repository;

    @Test
    void shouldSaveBook() {
        //given
        Auction auction = new Auction("test auction", 1.0, 1.0, "test description", LocalDateTime.now());
        Auction auction1 = new Auction("test auction1", 1.0, 1.0, "test description", LocalDateTime.now());
        Set<Auction> auctions = Set.of(auction, auction1);
        Seller bob = new Seller("Bob", auctions);

        //when
        Seller saved = repository.save(bob);

        //then
        assertEquals("Bob", saved.getName());
    }

}