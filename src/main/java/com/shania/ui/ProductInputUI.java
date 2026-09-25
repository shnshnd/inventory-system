package com.shania.ui;

import com.shania.service.ProductService;
import java.math.BigDecimal;
import java.util.Scanner;
public class ProductInputUI {
    private Scanner scan = new Scanner(System.in);
    private ProductService productService = new ProductService();

    public String readInput(){
        return scan.nextLine().trim();
    }

    public BigDecimal parseBigDecimal(String input) throws NumberFormatException {
        return new BigDecimal(input);
    }
    public int parseInt(String input) throws NumberFormatException{
        return Integer.parseInt(input);
    }

    public int requestValidId(){
        do{
            System.out.print("ID: ");
            String idInput = readInput();
            try{
                int id = parseInt(idInput);
                if(!productService.validateId(id)) System.out.println("Zero(0) or Negative are Not a Valid ID");
                else return id;
            } catch (NumberFormatException e) {
                System.out.println("Please Enter a Valid Whole Number");
            }
        }while(true);
    }
    public String requestValidName(){
        do {
            System.out.print("Name: ");
            String name = readInput();
            if(!productService.validateName(name)) System.out.println("Name Must Not Be Empty");
            else return name;
        }while (true);
    }
    public BigDecimal requestValidPrice(){
        do{
            System.out.print("Price: ");
            String priceInput = readInput();
            try {
                BigDecimal price = parseBigDecimal(priceInput);
                if(!productService.validatePrice(price)) System.out.println("Price Must Not Have a Value of Equal or Less Than Zero(0)");
                else return price;
            } catch (NumberFormatException e) {
                System.out.println("Please Enter a Valid Number");
            }
        }while(true);
    }
    public int requestValidQuantity(){
        do{
            System.out.print("Quantity: ");
            String quantityInput = readInput();
            try {
                int quantity = parseInt(quantityInput);
                if(!productService.validateQuantity(quantity)) System.out.println("Quantity Must Not Be Less Than Zero(0)");
                else return quantity;
            } catch(NumberFormatException e){
                System.out.println("Please Enter a Valid Whole Number");
            }
        }while(true);
    }
}