package com.sda.auctionsservice.auctions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    private SellerRepository repository;

    public SellerController(SellerRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Seller createSeller(@RequestBody Seller seller) {
        return repository.save(seller);
    }

}
