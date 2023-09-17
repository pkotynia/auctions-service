package com.sda.auctionsservice.auctions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// this is not very valid test it skips the Controller/Service layer where soem logic/validation is performed
// this is useful only for checking the DB layer
@SpringBootTest
class SellerRepositoryTest {

    @Autowired
    private SellerRepository repository;

    @Test
    void shouldSaveAuction() {
        //given
        Seller bob = new Seller("Bob", Set.of());

        //when
        Seller saved = repository.save(bob);

        //then
        assertEquals("Bob", saved.getName());
    }

}