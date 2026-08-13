package com.shania.ui;

import com.shania.dao.ProductDAO;
import com.shania.dao.ProductDAOImpl;
import com.shania.model.Product;
import com.shania.service.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private Scanner scan = new Scanner(System.in);
    private ProductService productService = new ProductService();
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductService productDAOService = new ProductService(productDAO);

    public  String inputEntry(Scanner scan){
        String inputEntry = scan.nextLine();
        if (!inputEntry.trim().isEmpty()) return inputEntry;
        return "";
    }
    public String readString(){
        try {
            return inputEntry(scan);
        }catch (NullPointerException e){
            return null;
        }
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
            String idInput = readString();
            try{
                int id = parseInt(idInput);
                if(!productDAOService.validateId(id)) System.out.println("Zero(0) or Negative are Not a Valid ID");
                else return id;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
        }while(true);
    }
    public String requestValidName(){
        do {
            System.out.print("Name: ");
            String name = readString();
            if(!productService.validateName(name)) System.out.println("Name Must Not Be Empty");
            else return name;
        }while (true);
    }
    public BigDecimal requestValidPrice(){
        do{
            System.out.print("Price: ");
            String priceInput = readString();
            try {
                BigDecimal price = parseBigDecimal(priceInput);
                if(!productService.validatePrice(price)) System.out.println("Price Must Not Have a Value of Equal or Less Than Zero(0)");
                else return price;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
            }
        }while(true);
    }
    public int requestValidQuantity(){
        do{
            System.out.print("Quantity: ");
            String quantityInput = readString();
            try {
                int quantity = parseInt(quantityInput);
                if(!productService.validateQuantity(quantity)) System.out.println("Quantity Must Not Be Less Than Zero(0)");
                else return quantity;
            } catch(NumberFormatException e){
                System.out.println("Invalid Input");
            }
        }while(true);
    }

    public boolean requestExistingProduct(int id){
        Product product = productDAOService.getExistingProduct(id);
        if(product == null) {
            System.out.println("Product ID: " + id + " Doesn't Exist");
            return false;
        }
        else {
            System.out.println("Product Details: ");
            System.out.println(product);
            return true;
        }
    }
    public void requestAllProducts(){
        List<Product> products = productDAOService.getAllExistingProducts();
        if(products.isEmpty()) {
            System.out.println("No Products Found");
        }else {
            for(Product product: products){
                System.out.println("Product Details: ");
                System.out.println(product);
            }
        }
    }
    public void requestProductToCreate(){
        String createName = requestValidName();
        BigDecimal createPrice = requestValidPrice();
        int createQuantity = requestValidQuantity();
        Product product = new Product(createName, createPrice, createQuantity);
        if(productDAOService.createProduct(product)) System.out.println("Product Added Successfully");
        else System.out.println("Failed to Add");
    }
    public void requestProductToUpdate(int id){
        if(requestExistingProduct(id)) {
            System.out.println("Update Product Details: ");
            String updateName = requestValidName();
            BigDecimal updatePrice = requestValidPrice();
            int updateQuantity = requestValidQuantity();
            Product product = new Product(id, updateName, updatePrice, updateQuantity);
            if(productDAOService.updateProduct(product)) System.out.println("Updated Successfully");
            else System.out.println("Failed to Update");
        }
    }
    public void requestProductToDelete(int id){
        if(requestExistingProduct(id)) {
            if(productDAOService.deleteProduct(id)) System.out.println("Deleted Successfully");
            else System.out.println("Failed to Delete");
        }
    }
}