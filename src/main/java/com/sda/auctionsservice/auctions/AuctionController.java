package com.sda.auctionsservice.auctions;

import com.sda.auctionsservice.CategoryNotFoundException;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService service;

    //Spring will provide concrete implementation of AuctionRepository
    public AuctionController(AuctionService service, AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.service = service;
    }

    @PostMapping
    public Auction postAuction(@RequestBody @Valid Auction auction) {
        return service.saveAuction(auction);
    }

    @PutMapping("/{id}")
    public Auction updateAuction(@PathVariable int id, @RequestBody @Valid Auction updatedAuction) {
        return service.updateAuction(updatedAuction, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuction(@PathVariable int id) {
        return service.deleteAuction(id);
    }

    @GetMapping
    public List<Auction> getAllAuctions() {
        return service.getAllAuctions();
    }

    @GetMapping("/searchByCategory")
    public List<Auction> getAuctionByCategory(@RequestParam("category") String categoryName) {
        //find category by name
        //find and return auctions by category
        return service.getAuctionByCategory(categoryName);
    }

    @GetMapping("/search")
    public List<Auction> getAuctionsByString(@RequestParam("query") String query) {
        return service.getAuctionsByQueryString(query);
    }

}
