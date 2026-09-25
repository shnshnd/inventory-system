package com.shania.ui;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;
public class ProductMenuUI {
    private Scanner scan = new Scanner(System.in);
    private ProductInputUI productInputUI = new ProductInputUI();
    private ProductUI productUI = new ProductUI();

    public void displayMenu(){
        System.out.println("* * * MENU * * *");
        System.out.println("1. Create Product \n2. View a Product\n3. View All Products\n4. Update Product\n5. Delete Product\n6. Exit");
    }
    public void startMenu(){
        boolean running = true;
        do {
            displayMenu();
            System.out.print("Enter: ");
            String actionInput = scan.nextLine();
            try{
                int action = Integer.parseInt(actionInput);
                if (action >= 1 && action <= 5) {
                    executeMenuOption(action);
                } else if (action == 6) {
                    System.out.println("Exiting...");
                    running = false;
                } else {
                    System.out.println("Not Part of the Menu Options");
                }
            }catch(NumberFormatException e){
                System.out.println("Please Enter a Valid Input");
            }
        }
        while (running);
    }

    public void executeMenuOption(int action){
        switch (action){
            case 1 -> createProduct();
            case 2 -> viewProduct();
            case 3 -> viewAllProduct();
            case 4 -> updateProduct();
            case 5 -> deleteProduct();
            default -> System.out.println("Not Part of the Menu Options");
        }
    }
    public void createProduct(){
        try{
            System.out.println("* * * Create a Product * * *");
            System.out.println("Enter the Product Details");
            String createName = productInputUI.requestValidName();
            BigDecimal createPrice = productInputUI.requestValidPrice();
            int createQuantity = productInputUI.requestValidQuantity();
            System.out.println("Enter (\"Y\") to Create | Enter Any Character to Exit: ");
            String create = scan.nextLine();
            if (create.equalsIgnoreCase("Y")) {
                productUI.createProduct(createName, createPrice, createQuantity);
            } else System.out.println("Stopping...");
        }catch(SQLException e){
            System.out.println("Unable to Process Product Creation Request");
        }
    }

    public void viewProduct(){
        try{
            System.out.println("* * * Product Details * * *");
            System.out.println("Enter the Product ID You Want to Display");
            int id = productInputUI.requestValidId();
            productUI.displayVerifiedProduct(id);
        }catch(SQLException e){
            System.out.println("Unable to Retrieve the Product");
        }
    }
    public void viewAllProduct(){
        try{
            System.out.println("* * * List of Products * * *");
            productUI.displayAllProducts();
        }catch(SQLException e){
            System.out.println("Unable to Retrieve All Products");
        }
    }
    public void updateProduct(){
        System.out.println("* * * Update a Product * * *");
        System.out.println("Enter the Product ID to Update");
        int id = productInputUI.requestValidId();
        try{
            if (productUI.displayVerifiedProduct(id)) {
                System.out.println("Update Product Details: ");
                String updateName = productInputUI.requestValidName();
                BigDecimal updatePrice = productInputUI.requestValidPrice();
                int updateQuantity = productInputUI.requestValidQuantity();
                System.out.println("Enter (\"Y\") to Update | Enter Any Character to Exit: ");
                String update = scan.nextLine();
                if (update.equalsIgnoreCase("Y")) {
                    productUI.updateProduct(id, updateName, updatePrice, updateQuantity);
                } else System.out.println("Stopping...");
            }
        }catch(SQLException e){
            System.out.println("Unable to Process Product Update Request");
        }
    }
    public void deleteProduct(){
        try{
            System.out.println("* * * Delete a Product * * *");
            System.out.println("Enter the Product ID to Delete");
            int id = productInputUI.requestValidId();
            if(productUI.displayVerifiedProduct(id)) {
                System.out.println("Enter (\"Y\") to Delete | Enter Any Character to Exit: ");
                String delete = scan.nextLine();
                if (delete.equalsIgnoreCase("Y")) {
                    productUI.deleteProduct(id);
                } else System.out.println("Stopping...");
            }
        }catch(SQLException e){
            System.out.println("Unable to Process Product Deletion Request");
        }
    }
}
