package com.sda.auctionsservice.auctions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionRepository repository;

    //Spring will provide concrete implementation of AuctionRepository
    public AuctionController(AuctionRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Auction postAuction(@RequestBody Auction auction) {
        return repository.save(auction);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuction(@PathVariable int id) {
//        if (repository.findById(id).isPresent()) {
//            repository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }

        return repository.findById(id)
                .map(auction -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
