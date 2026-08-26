package com.shania.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductMenuUI {
    private Scanner scan = new Scanner(System.in);
    private ProductUI productUI = new ProductUI();

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
        int id;
        String name;
        BigDecimal price;
        int quantity;
        switch (action){
            case 1 -> {
                System.out.println("* * * Create a Product * * *");
                System.out.println("Enter the Product Details");
                productUI.requestProductToCreate();
            }
            case 2 -> {
                System.out.println("* * * Product Details * * *");
                System.out.println("Enter the Product ID You Want to Display");
                productUI.requestExistingProduct();
            }
            case 3 -> {
                System.out.println("* * * List of Products * * *");
                productUI.requestAllProducts();
            }
            case 4 -> {
                System.out.println("* * * Update a Product * * *");
                System.out.println("Enter the Product ID to Update");
                productUI.requestProductToUpdate();
            }
            case 5 -> {
                System.out.println("* * * Delete a Product * * *");
                System.out.println("Enter the Product ID to Delete");
                productUI.requestProductToDelete();
            }
            default -> System.out.println("Not Part of the Menu Options");
        }
    }
}
