package com.shania.ui;

import com.shania.dao.ProductDAO;
import com.shania.dao.ProductDAOImpl;
import com.shania.model.Product;
import com.shania.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductUI {
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductService productService = new ProductService(productDAO);
    private ProductInputUI productInputUI = new ProductInputUI();
    private Scanner scan = new Scanner(System.in);

    public boolean requestIDVerification(int id) throws SQLException{
        Product product = productService.getExistingProduct(id);
        if(product != null) {
            System.out.println("Product ID: " + id + " Exist");
            return true;
        }else {
            System.out.println("Product ID: " + id + " Doesn't Exist");
            return false;
        }
    }
    public int requestVerifiedProductID() throws SQLException{
        int id = productInputUI.requestValidId();
        if(requestIDVerification(id)) return id;
        else throw new SQLException();
    }

    public void printExistingProduct(int id) throws SQLException{
        Product product = productService.getExistingProduct(id);
        System.out.println(product);
    }

    public void requestExistingProduct() {
        try {
            int verifiedId = requestVerifiedProductID();
            printExistingProduct(verifiedId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void requestAllProducts(){
        try{
            List<Product> products = productService.getAllExistingProducts();
            if (products.isEmpty()) {
                System.out.println("No Products Found");
            } else {
                for (Product product : products) {
                    System.out.println("Product Details: ");
                    System.out.println(product);
                }
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void requestProductToCreate(){
        try{
            String createName = productInputUI.requestValidName();
            BigDecimal createPrice = productInputUI.requestValidPrice();
            int createQuantity = productInputUI.requestValidQuantity();
            System.out.println("Enter (\"Y\") to Update | Enter Any Character to Exit: ");
            String create = scan.nextLine().toUpperCase();
            if (create.equals("Y")) {
                Product product = new Product(createName, createPrice, createQuantity);
                if (productService.createProduct(product)) System.out.println("Product Created Successfully");
                else System.out.println("Failed to Create");
            } else System.out.println("Stopping...");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void requestProductToUpdate(){
        try {
            int id = requestVerifiedProductID();
            System.out.println("Product Details: ");
            printExistingProduct(id);
            System.out.println("Update Product Details: ");
            String updateName = productInputUI.requestValidName();
            BigDecimal updatePrice = productInputUI.requestValidPrice();
            int updateQuantity = productInputUI.requestValidQuantity();
            System.out.println("Enter (\"Y\") to Update | Enter Any Character to Exit: ");
            String update = scan.nextLine().toUpperCase();
            if (update.equals("Y")) {
                Product product = new Product(id, updateName, updatePrice, updateQuantity);
                if (productService.updateProduct(product)) System.out.println("Updated Successfully");
                else System.out.println("Failed to Update");
            }
            else System.out.println("Stopping...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public void requestProductToDelete() {
        try{
            int id = requestVerifiedProductID();
                System.out.println("Product Details: ");
                printExistingProduct(id);
                System.out.println("Enter (\"Y\") to Delete | Enter Any Character to Exit: ");
                String delete = scan.nextLine().toUpperCase();
                if(delete.equals("Y")) {
                    if(productService.deleteProduct(id)) System.out.println("Deleted Successfully");
                    else System.out.println("Unsuccessful to Delete");
                }
                else System.out.println("Stopping...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
