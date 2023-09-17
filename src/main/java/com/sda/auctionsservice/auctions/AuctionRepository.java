package com.sda.auctionsservice.auctions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * This interface is responsible for providing methods like save, findAll, delete() and many more
 * implementation of those methods is provided By JPA
 */
public interface AuctionRepository extends JpaRepository<Auction, Integer> {

    //currently we have implicitly all the metods from JpaRepository and extended repositories available
    // for example Auction save(Auction entity);
    // of Auction findById(Integer id);

    List<Auction> findByCategory(Category category);
}
