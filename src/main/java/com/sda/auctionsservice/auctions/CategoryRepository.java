package com.sda.auctionsservice.auctions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // we are using JPA method manes convention to create method that will allow us to search category by name
    // by default findByName is not present
    // implementation of this method will be provided by Spring
    Optional<Category> findByName(String name);

}
