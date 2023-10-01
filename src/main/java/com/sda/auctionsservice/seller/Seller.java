package com.sda.auctionsservice.seller;

import com.sda.auctionsservice.auctions.Auction;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
//@Table(name = "name different than class")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sellerId;

    @NotNull
    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "seller_auction",
            joinColumns = @JoinColumn(name = "seller_id"),
            inverseJoinColumns = @JoinColumn(name = "auction_id")
    )
    private Set<Auction> auctions = new HashSet<>();

    public Seller(String name, Set<Auction> auctions) {
        this.name = name;
        this.auctions = auctions;
    }

    public Seller() {

    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(Set<Auction> auctions) {
        this.auctions = auctions;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "sellerId=" + sellerId +
                ", name='" + name + '\'' +
                ", auctions=" + auctions +
                '}';
    }
}
