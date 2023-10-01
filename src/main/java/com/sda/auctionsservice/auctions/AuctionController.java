package com.sda.auctionsservice.auctions;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * this controller is an entry point for handling CRUD operation on Auctions
 *
 * RestController = Controller(notifies Spring about defined endpoints) + ResponseBody(converts return types form methods into JSONs)
 * RequestMapping defines main endpoint for this controller
 */
@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService service;

    // Dependency injection of service object
    public AuctionController(AuctionService service) {
        this.service = service;
    }

    /**
     * 1. receives HTTP request type Post with request body containing JSON format of Auction abject
     * 2. JSON is converted into Auction object (@RequestBody)
     * 3. Validation is performed base on Hibernate Validator annotation in Auction Object (@Valid)
     * 4. Auction is saved AuctionsService.saveAuction() -> AuctionRepository.save()
     * 5. Saved Auction object is returned
     *
     * @param auction - auction to be saved
     * @return saved auction
     */
    @PostMapping // defines HTTP request type
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
