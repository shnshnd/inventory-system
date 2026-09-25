package com.shania.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;//double is acceptable, but it is a better practice using BigDecimal in Real Systems especially in Financial Systems
    private int stockQuantity;
    private LocalDateTime dateCreated;

    public Product() {}

    //For inserting new products
    public Product(String name, BigDecimal price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    //For updating
    public Product(int id, String name, BigDecimal price, int stockQuantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    //For db retrieval
    public Product(int id, String name, BigDecimal price, int stockQuantity, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) { this.price = price; }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nPrice: " + price + "\nStock: " + stockQuantity + "\nDate & Time Created: " + dateCreated;
    }
}

