package com.sda.auctionsservice.seller;

import com.sda.auctionsservice.auctions.Auction;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class SellerService {

    private final SellerRepository repository;

    public SellerService(SellerRepository repository) {
        this.repository = repository;
    }

    Seller save(Seller seller) {
        return repository.save(seller);
    }

    Set<Auction> findById(Integer id) {
        //Find if seller exist
        //Get auctions for seller
        //declarative style
//        return repository.findById(id)
//                .orElseThrow(() -> new ObjectNotFoundException(id, "seller not found"))
//                .getAuctions();

        //imperative style
        Optional<Seller> sellerFromRepo = repository.findById(id);
        if (sellerFromRepo.isPresent()) {
            Seller unwrapedSeller = sellerFromRepo.get();
            return unwrapedSeller.getAuctions();
        } else {
            throw new ObjectNotFoundException(id, "seller not found");
        }
    }
}
