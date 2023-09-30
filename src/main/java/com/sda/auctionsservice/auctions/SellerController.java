package com.sda.auctionsservice.auctions;

import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private SellerRepository repository;

    public SellerController(SellerRepository repository) {
        this.repository = repository;
    }

    //TODO - refactor this code and create service layer
    @PostMapping
    public Seller createSeller(@RequestBody @Valid Seller seller) {
        return repository.save(seller);
    }

    @GetMapping("/{id}/auctions")
    public Iterable<Auction> getAuctionsForSeller(@PathVariable Integer id) {
        //Find if seller exist
        //Get auctions for seller
        //declarative style
//        return repository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException(id, "seller not found"))
//                .getAuctions();

        //imperative style
        Optional<Seller> seller = repository.findById(id);

        if (seller.isPresent()) {
            Seller unwrapedSeller = seller.get();
            return unwrapedSeller.getAuctions();
        } else {
            throw new ObjectNotFoundException(id, "seller not found");
        }
    }

}
