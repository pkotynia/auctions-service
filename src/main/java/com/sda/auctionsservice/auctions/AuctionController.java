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

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    //Spring will provide concrete implementation of AuctionRepository
    public AuctionController(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping
    public Auction postAuction(@RequestBody @Valid Auction auction) {
//        //check if category exists
//        Optional<Category> categoryOptional = categoryRepository.findByName(auction.getCategory().getName());
//
//        //else throw category not exist exception
//        Category category = categoryOptional.orElseThrow(() -> new ObjectNotFoundException(auction, "category not found"));
//
//        //if exists - set category on auction
//        auction.setCategory(category);
//
//        return auctionRepository.save(auction);

        return categoryRepository.findByName(auction.getCategory().getName())
                .map(category -> setCategoryForAuction(auction, category))
                .orElseThrow(() -> new CategoryNotFoundException("Category " + auction.getCategory().getName() + " not exist"));
    }

    private Auction setCategoryForAuction(Auction auction, Category category) {
        auction.setCategory(category);
        return auctionRepository.save(auction);
    }

    @PutMapping("/{id}")
    public Auction updateAuction(@PathVariable int id, @RequestBody @Valid Auction updatedObject) {
//        //fetch auction from repo by id
//        Auction auctionFromRepo = auctionRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, " auction not found"));
//        //update auction object
//        auctionFromRepo.setName(updatedObject.getName());
//        auctionFromRepo.setCurrentPrice(updatedObject.getCurrentPrice());
//        auctionFromRepo.setInitialPrice(updatedObject.getInitialPrice());
//        auctionFromRepo.setDescription(updatedObject.getDescription());
//        auctionFromRepo.setEndTime(updatedObject.getEndTime());
//
//        //save updated object
//        return auctionRepository.save(auctionFromRepo);

        return auctionRepository.save(findAuctionByIdAndUpdateFields(id, updatedObject));
    }

    private Auction findAuctionByIdAndUpdateFields(int id, Auction updatedObject) {
        return auctionRepository.findById(id)
                .map(auctionFromRepo -> updateAuction(updatedObject, auctionFromRepo))
                .orElseThrow(() -> new ObjectNotFoundException(id, " auction not found"));
    }

    private static Auction updateAuction(Auction updatedObject, Auction auctionFromRepo) {
        auctionFromRepo.setName(updatedObject.getName());
        auctionFromRepo.setCurrentPrice(updatedObject.getCurrentPrice());
        auctionFromRepo.setInitialPrice(updatedObject.getInitialPrice());
        auctionFromRepo.setDescription(updatedObject.getDescription());
        auctionFromRepo.setEndTime(updatedObject.getEndTime());
        return auctionFromRepo;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAuction(@PathVariable int id) {
//        if (repository.findById(id).isPresent()) {
//            repository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }

        return auctionRepository.findById(id)
                .map(auction -> {
                    auctionRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

}
