package com.sda.auctionsservice.auctions;

import com.sda.auctionsservice.exceptions.CategoryNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // this annotation is telling spring that this is a component and should be included in spring context during app startup
public class AuctionService {

    // this is an example of has-a (composition) relation
    private final AuctionRepository auctionRepository;

//    @Autowired - code smell, this method is not recommended - makes testing harder
    private final CategoryRepository categoryRepository;

    // implicit autowired operation done by spring. No need to provide @Autowire annotation
    public AuctionService(AuctionRepository auctionRepository, CategoryRepository categoryRepository) {
        this.auctionRepository = auctionRepository;
        this.categoryRepository = categoryRepository;
    }

    // In service we provide methods that are then ussed by Controllers
    //Methods should contain business logic
    //we are using default access modifier, only classes form current package will be able to access this method
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

    List<Auction> getAuctionsByQueryString(String query) {
        return auctionRepository.findAuctionBy(query);
    }


}
