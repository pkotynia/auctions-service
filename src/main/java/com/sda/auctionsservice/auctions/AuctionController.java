package com.sda.auctionsservice.auctions;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionRepository repository;

    public AuctionController(AuctionRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Auction postAuction(@RequestBody Auction auction) {
        return repository.save(auction);
    }
}
