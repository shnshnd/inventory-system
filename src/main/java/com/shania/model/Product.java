package com.shania.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
    private int id;
    private String name;
    private BigDecimal price;//double is acceptable, but it is a better practice to use BigDecimal in Real Systems especially in Financial Systems
    private int stockQuantity;
    private LocalDateTime dateCreated;

    public Product() {
    }

    //For inserting new products
    public Product(String name, BigDecimal price, int stockQuantity) {
        this.name = name;
        this.setPrice(price);
        this.stockQuantity = stockQuantity;
    }
    //For updating
    public Product(int id, String name, BigDecimal price, int stockQuantity){
        this.id = id;
        this.name = name;
//        this.setPrice(price);
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    //For db retrieval
    public Product(int id, String name, BigDecimal price, int stockQuantity, LocalDateTime dateCreated) {
        this.id = id;
        this.name = name;
//        this.price = price;
        this.setPrice(price);
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

    public void setPrice(BigDecimal price) {
//        BigDecimal BDprice = BigDecimal.valueOf(price);
        this.price = price;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
    //Every Java class extends Object class
    //One of the methods in Object class is toString()
    //@Override annotation will help tell the compiler that this is overriding the method from the parent class. However, with or without the @Override annotation the method will still run as long as the exact method signature is recognized by Java compiler. Should be toString() and not tostring() because method signature is what being followed and recognized by the compiler
    //without the toString() the default output would look like (Classname + @ + hashCode = "Product@1a2b3c")
    //with toString() you will be able to perform the expected method, and you will see the expected output, and if there's an error the compiler will immediately show an error.
    @Override
    public String toString() {
        return "ID: " + this.id + "\nName: " + this.name + "\nPrice: " + price + "\nStock: " + stockQuantity + "\nDate & Time Created: " + dateCreated;
    }
}

