package com.sda.auctionsservice.auctions;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//this test was created as a part of devlepoment effort to check how repository works
@SpringBootTest
class AuctionRepositoryTest {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldGetAuctionsByCategory() {

        Optional<Category> motoCategory = categoryRepository.findByName("Moto");

        List<Auction> res = auctionRepository.findByCategory(motoCategory.get());

        assertEquals(2, res.size());
    }

    @Test
    void shouldFindAuctionByQueryString() {

        List<Auction> auctions = auctionRepository.findAuctionBy("toyota");

        assertEquals(1, auctions.size());
    }

}