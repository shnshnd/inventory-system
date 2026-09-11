package com.shania.ui;

import com.shania.dao.ProductDAO;
import com.shania.dao.ProductDAOImpl;
import com.shania.model.Product;
import com.shania.service.ProductService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class ProductUI {
    private ProductDAO productDAO = new ProductDAOImpl();
    private ProductService productService = new ProductService(productDAO);

    public boolean requestVerifiedProduct(int id) throws SQLException{
        Product product = productService.getExistingProduct(id);
        if (product != null) {
            System.out.println("Product ID: " + id + " Exist");
            System.out.println("Product Details: ");
            System.out.println(product);
            return true;
        }
        else {
            System.out.println("Product ID: " + id + " Doesn't Exist");
            return false;
        }
    }
    public void requestAllProducts() throws SQLException{
        List<Product> products = productService.getAllExistingProducts();
        if (products.isEmpty()) {
            System.out.println("No Products Found");
        } else {
            for (Product product : products) {
                System.out.println("Product Details: ");
                System.out.println(product);
            }
        }
    }
    public void requestProductToCreate(String createName, BigDecimal createPrice, int createQuantity) throws SQLException{
        Product product = new Product(createName, createPrice, createQuantity);
        if (productService.createProduct(product)) System.out.println("Successfully Created");
        else System.out.println("Unable to Create the Product");
    }
    public void requestProductToUpdate(int id, String nameToUpdate,BigDecimal priceToUpdate, int quantityToUpdate) throws SQLException{
        Product productUpdate = new Product(id, nameToUpdate, priceToUpdate, quantityToUpdate);
        if (productService.updateProduct(productUpdate)) System.out.println("Successfully Updated");
        else System.out.println("Unable to Update the Product");
    }
    public void requestProductToDelete(int id) throws SQLException{
        if(productService.deleteProduct(id)) System.out.println("Successfully Deleted");
        else System.out.println("Unable to Delete the Product");
    }
}
