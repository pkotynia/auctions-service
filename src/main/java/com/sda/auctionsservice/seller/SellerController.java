package com.sda.auctionsservice.seller;

import com.sda.auctionsservice.auctions.Auction;
import jakarta.validation.Valid;
import org.hibernate.ObjectNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerService service;

    public SellerController(SellerService service) {
        this.service = service;
    }

    @PostMapping
    public Seller createSeller(@RequestBody @Valid Seller seller) {
        return service.save(seller);
    }

    @GetMapping("/{id}/auctions")
    public Iterable<Auction> getAuctionsForSeller(@PathVariable Integer id) {
       return service.findById(id);
    }

}
