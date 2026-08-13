package com.shania;
import com.shania.ui.InputHandler;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        InputHandler inputHandler = new InputHandler();
        startMenu(scan,inputHandler);
    }

    public static void displayMenu(){
        System.out.println("* * * MENU * * *");
        System.out.println("1. Create Product \n2. View a Product\n3. View All Products\n4. Update Product\n5. Delete Product\n6. Exit");
    }

    public static void startMenu(Scanner scan, InputHandler inputHandler){
        boolean running = true;
        do {
            displayMenu();
            System.out.print("Enter: ");
            String actionInput = scan.nextLine();
            try{
                int action = Integer.parseInt(actionInput);
                if (action >= 1 && action <= 5) {
                    menuOptions(action, inputHandler);
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

    public static void menuOptions(int action, InputHandler inputHandler){
        int id;
        switch (action){
            case 1 -> {
                System.out.println("* * * Create a Product * * *");
                System.out.println("Enter the Product Details");
                inputHandler.requestProductToCreate();
            }
            case 2 -> {
                System.out.println("* * * Product Details * * *");
                System.out.println("Enter the Product ID You Want to Display");
                id = inputHandler.requestValidId();
                inputHandler.requestExistingProduct(id);
            }
            case 3 -> {
                System.out.println("* * * List of Products * * *");
                inputHandler.requestAllProducts();
            }
            case 4 -> {
                System.out.println("* * * Update a Product * * *");
                System.out.println("Enter the Product ID to Update");
                id = inputHandler.requestValidId();
                inputHandler.requestProductToUpdate(id);
            }
            case 5 -> {
                System.out.println("* * * Delete a Product * * *");
                System.out.println("Enter the Product ID to Delete");
                id = inputHandler.requestValidId();
                inputHandler.requestProductToDelete(id);
            }
            default -> System.out.println("Not Part of the Menu Options");
        }
    }
}
