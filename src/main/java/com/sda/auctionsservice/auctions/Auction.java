package com.sda.auctionsservice.auctions;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
//Deprecated do not ever use
// import java.util.Date

@Entity
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Digits(integer = 6, fraction = 2)
    @DecimalMin(value = "0.01")
    @DecimalMax(value = "999999.99")
    private BigDecimal initialPrice;

    private BigDecimal currentPrice;

    private String description;

    private LocalDateTime endTime;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    public Auction() {}

    public Auction(String name, BigDecimal initialPrice, BigDecimal currentPrice, String description, LocalDateTime endTime, Category category) {
        this.name = name;
        this.initialPrice = initialPrice;
        this.currentPrice = currentPrice;
        this.description = description;
        this.endTime = endTime;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", initialPrice=" + initialPrice +
                ", currentPrice=" + currentPrice +
                ", description='" + description + '\'' +
                ", currentTime=" + endTime +
                '}';
    }
}
