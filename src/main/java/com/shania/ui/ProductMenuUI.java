package com.shania.ui;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductMenuUI {
    private Scanner scan = new Scanner(System.in);
    private ProductInputUI prodInputUI = new ProductInputUI();
    private ProductUI prodUI = new ProductUI();

    public void menuList(){
        System.out.println("* * * MENU * * *");
        System.out.println("1. Create Product \n2. View a Product\n3. View All Products\n4. Update Product\n5. Delete Product\n6. Exit");
    }
    public void startMenu(){
        boolean running = true;
        do {
            menuList();
            System.out.print("Enter: ");
            String actionInput = scan.nextLine();
            try{
                int action = Integer.parseInt(actionInput);
                if (action >= 1 && action <= 5) {
                    menuOptions(action);
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

    public void menuOptions(int action){
        switch (action){
            case 1 -> optionCreateProduct();
            case 2 -> optionViewProduct();
            case 3 -> optionViewAllProduct();
            case 4 -> optionUpdateProduct();
            case 5 -> optionDeleteProduct();
            default -> System.out.println("Not Part of the Menu Options");
        }
    }
    public void optionCreateProduct(){
        try{
            System.out.println("* * * Create a Product * * *");
            System.out.println("Enter the Product Details");
            String createName = prodInputUI.requestValidName();
            BigDecimal createPrice = prodInputUI.requestValidPrice();
            int createQuantity = prodInputUI.requestValidQuantity();
            System.out.println("Enter (\"Y\") to Create | Enter Any Character to Exit: ");
            String create = scan.nextLine().toUpperCase();
            if (create.equals("Y")) {
                prodUI.requestProductToCreate(createName, createPrice, createQuantity);
            } else System.out.println("Stopping...");
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("-");
            System.out.println(e.getMessage());
            System.out.println("-");
            System.out.println(e.getCause());
        }
    }

    public void optionViewProduct(){
        try{
            System.out.println("* * * Product Details * * *");
            System.out.println("Enter the Product ID You Want to Display");
            int id = prodInputUI.requestValidId();
            prodUI.requestVerifiedProduct(id);
        }catch(SQLException e){

            System.out.println(e);
            System.out.println("--");
            System.out.println(e.getMessage());
            System.out.println("--");
            System.out.println(e.getCause());
        }
    }
    public void optionViewAllProduct(){
        try{
            System.out.println("* * * List of Products * * *");
            prodUI.requestAllProducts();
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("---");
            System.out.println(e.getMessage());
            System.out.println("---");
            System.out.println(e.getCause());

        }
    }
    public void optionUpdateProduct(){
        System.out.println("* * * Update a Product * * *");
        System.out.println("Enter the Product ID to Update");
        int id = prodInputUI.requestValidId();
        try{
            if (prodUI.requestVerifiedProduct(id)) {
                System.out.println("Update Product Details: ");
                String updateName = prodInputUI.requestValidName();
                BigDecimal updatePrice = prodInputUI.requestValidPrice();
                int updateQuantity = prodInputUI.requestValidQuantity();
                System.out.println("Enter (\"Y\") to Update | Enter Any Character to Exit: ");
                String update = scan.nextLine().toUpperCase();
                if (update.equals("Y")) {
                    prodUI.requestProductToUpdate(id, updateName, updatePrice, updateQuantity);
                } else System.out.println("Stopping...");
            }
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("----");
            System.out.println(e.getMessage());
            System.out.println("----");
            System.out.println(e.getCause());
        }
    }
    public void optionDeleteProduct(){
        try{
            System.out.println("* * * Delete a Product * * *");
            System.out.println("Enter the Product ID to Delete");
            int id = prodInputUI.requestValidId();
            if(prodUI.requestVerifiedProduct(id)) {
                System.out.println("Enter (\"Y\") to Delete | Enter Any Character to Exit: ");
                String delete = scan.nextLine().toUpperCase();
                if (delete.equals("Y")) {
                    prodUI.requestProductToDelete(id);
                } else System.out.println("Stopping...");
            }
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("-----");
            System.out.println(e.getMessage());
            System.out.println("-----");
            System.out.println(e.getCause());
        }
    }

}
