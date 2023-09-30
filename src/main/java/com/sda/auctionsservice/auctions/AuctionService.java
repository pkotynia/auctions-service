package com.sda.auctionsservice.auctions;

import com.sda.auctionsservice.CategoryNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public AuctionService(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    Auction saveAuction(Auction auction) {
//        check if category exists
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
                .map(category -> setCategoryForAuctionAndSaveToRepo(auction, category))
                .orElseThrow(() -> new CategoryNotFoundException("Category " + auction.getCategory().getName() + " not exist"));
    }

    private Auction setCategoryForAuctionAndSaveToRepo(Auction auction, Category category) {
        auction.setCategory(category);
        return auctionRepository.save(auction);
    }

    Auction updateAuction(Auction auction, Integer id) {
        //        fetch auction from repo by id
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
        return auctionRepository.save(findAuctionByIdAndUpdateFields(id, auction));
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

    ResponseEntity<Object> deleteAuction(Integer id) {
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

    List<Auction> getAllAuctions() {
        return auctionRepository.findAll();
    }

    List<Auction> getAuctionByCategory(String categoryName) {
        return auctionRepository
                .findByCategory(getCategoryByNameOrElseThrowException(categoryName));
    }

    private Category getCategoryByNameOrElseThrowException(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() -> new CategoryNotFoundException("Category " + categoryName + " not exist"));
    }



}
